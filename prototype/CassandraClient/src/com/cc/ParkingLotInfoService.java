package com.cc;

import java.util.LinkedList;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;

public class ParkingLotInfoService {

	public List<ParkingLotInfo> getParklingLotInfoByZoneId(String zoneId) {
		List<ParkingLotInfo> lotInfos = new LinkedList<ParkingLotInfo>();
		
		Cluster cluster = HFactory.getOrCreateCluster("Test Cluster", new CassandraHostConfigurator("localhost:9160"));
		Keyspace keyspace = HFactory.createKeyspace("ParkingIQ", cluster);
		StringSerializer stringSerializer = StringSerializer.get();
		
		QueryResult<OrderedRows<String, String, String>> result = HFactory.createIndexedSlicesQuery(
				keyspace, 
				stringSerializer, 
				stringSerializer, 
				stringSerializer)
			.addEqualsExpression("zoneid", zoneId)
			.setColumnFamily("ParkingLot")
			.setColumnNames("type", "latitude", "longitude")
			.setStartKey("")
			.execute();
		
		for (Row<String, String, String> row : result.get()) {
			ParkingLotInfo lotInfo = null;
			try {
				lotInfo = ParkingLotInfo.create(
						row.getKey(),
						row.getColumnSlice().getColumnByName("type").getValue(),
						row.getColumnSlice().getColumnByName("latitude").getValue(),
						row.getColumnSlice().getColumnByName("longitude").getValue());
			} catch (NumberFormatException e) {
				System.out.println(String.format("Bad record for key %1s because %2s", row.getKey(), e.getMessage()));
				continue;
			}
			lotInfos.add(lotInfo);
		}
		return lotInfos;
	}
	
}
