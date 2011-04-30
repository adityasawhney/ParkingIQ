/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.service.impl;

import java.util.LinkedList;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;
import edu.colorado.piq.Const.DataModel.CF_ParkingLot;
import edu.colorado.piq.model.CassandraConfig;
import edu.colorado.piq.model.ParkingLotInfo;
import edu.colorado.piq.model.Zone;
import edu.colorado.piq.service.ParkingLotInfoService;
import edu.colorado.piq.util.CassandraUtil;

/**
 * Implementation of ParkingLotInfoService interface.
 */
public class ParkingLotInfoServiceImpl implements ParkingLotInfoService {
	
	/** Used to connect to cassandra. */
	private CassandraConfig cassandraConfig;
	
	/**
	 * Instantiates a new parking lot info service impl.
	 *
	 * @param config the config
	 */
	public ParkingLotInfoServiceImpl(CassandraConfig config) {
		this.cassandraConfig = config;
	}
	
	/* (non-Javadoc)
	 * @see edu.colorado.piq.service.ParkingLotInfoService#getParklingLotInfo(edu.colorado.piq.model.Zone)
	 */
	public List<ParkingLotInfo> getParkingLotInfo(Zone zone) {
		List<ParkingLotInfo> lotInfos = new LinkedList<ParkingLotInfo>();

		// Return empty list if zone is invalid
		if (!zone.isValid()) return lotInfos;

		Keyspace keyspace = CassandraUtil.Connect(cassandraConfig);
		StringSerializer stringSerializer = StringSerializer.get();
		
		// Get parking lot information (type, latitude, longitude) which have
		// the same zoneid as the given zone id.
		//
		// In SQL terms,
		// 	select type, latitude, longitude from ParkingLot
		// 	where zoneid = {given-zone-id}
		QueryResult<OrderedRows<String, String, String>> result = HFactory.createIndexedSlicesQuery(
				keyspace, 
				stringSerializer, 
				stringSerializer, 
				stringSerializer)
			.addEqualsExpression(CF_ParkingLot.Col.ZONEID, zone.getZoneId()) // where clause
			.setColumnFamily(CF_ParkingLot.NAME) // from clause
			.setColumnNames( // select clause
					CF_ParkingLot.Col.TYPE, 
					CF_ParkingLot.Col.LATITUDE, 
					CF_ParkingLot.Col.LONGITUDE)
			.setStartKey("")
			.execute();
		
		// Extract info from the result set and map it into the corresponding object model
		for (Row<String, String, String> row : result.get()) {
			ParkingLotInfo lotInfo = null;
			try {
				lotInfo = ParkingLotInfo.create(
						row.getKey(),
						row.getColumnSlice().getColumnByName(CF_ParkingLot.Col.TYPE).getValue(),
						row.getColumnSlice().getColumnByName(CF_ParkingLot.Col.LATITUDE).getValue(),
						row.getColumnSlice().getColumnByName(CF_ParkingLot.Col.LONGITUDE).getValue());
			} catch (NumberFormatException e) {
				// Skip the bad records
				System.out.println(String.format("Bad record for key %1s because %2s", row.getKey(), e.getMessage()));
				continue;
			}
			lotInfos.add(lotInfo);
		}

		return lotInfos;
	}

	@Override
	public ParkingLotInfo getParkingLotInfo(int lotId) {
		String lotIdStr = Integer.toString(lotId);
		Keyspace keyspace = CassandraUtil.Connect(cassandraConfig);
		StringSerializer stringSerializer = StringSerializer.get();
		QueryResult<ColumnSlice<String, String>> result = HFactory.createSliceQuery(
				keyspace, 
				stringSerializer, 
				stringSerializer, 
				stringSerializer)
			.setColumnFamily(CF_ParkingLot.NAME) // from clause
			.setKey(lotIdStr)
			.setColumnNames(
					CF_ParkingLot.Col.TYPE,
					CF_ParkingLot.Col.LATITUDE, 
					CF_ParkingLot.Col.LONGITUDE)
			.execute();
		
		ParkingLotInfo lotInfo = null;
		try {
			lotInfo = ParkingLotInfo.create(
					lotIdStr,
					result.get().getColumnByName(CF_ParkingLot.Col.TYPE).getValue(),
					result.get().getColumnByName(CF_ParkingLot.Col.LATITUDE).getValue(),
					result.get().getColumnByName(CF_ParkingLot.Col.LONGITUDE).getValue());
		} catch (NumberFormatException e) {
			// Skip the bad records
			System.out.println(String.format("Bad record for key %1s because %2s", lotIdStr, e.getMessage()));
		}
		return lotInfo;
	}
}
