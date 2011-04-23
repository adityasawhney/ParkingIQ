package com.cc;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;

public class ParkingLotStatusService {

	public void updateParkingLotStatus(List<ParkingLotInfo> parkingLots) {
		Cluster cluster = HFactory.getOrCreateCluster("Test Cluster", new CassandraHostConfigurator("localhost:9160"));
		Keyspace keyspace = HFactory.createKeyspace("ParkingIQ", cluster);
		StringSerializer stringSerializer = StringSerializer.get();
		List<String> keyRange = compileKeyRanges(parkingLots);
		
		QueryResult<Rows<String, String, String>> result = HFactory.createMultigetSliceQuery(
				keyspace, 
				stringSerializer, 
				stringSerializer, 
				stringSerializer)
			.setColumnFamily("LotStatusArchive")
			.setKeys(keyRange)
			.setRange("", "", true, 1)
			.execute();
		
		for (Row<String, String, String> row : result.get()) {
			if (!row.getColumnSlice().getColumns().isEmpty()) {
				String availableSpaceStr = row.getColumnSlice().getColumns().get(0).getValue();
				parkingLots.get(0).setAvailableSpace(Integer.parseInt(availableSpaceStr));
			}
			else {
				System.out.println(String.format("No data found for key %1s", row.getKey()));
			}
		}
	}
	
	private List<String> compileKeyRanges(List<ParkingLotInfo> parkingLots) {
		List<String> keyRanges = new ArrayList<String>(parkingLots.size());
		
		for (ParkingLotInfo lotInfo : parkingLots) {
			keyRanges.add(Integer.toString(lotInfo.getLotId()));
		}
		return keyRanges;
	}
}
