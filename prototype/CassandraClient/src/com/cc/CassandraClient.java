package com.cc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import me.prettyprint.cassandra.serializers.IntegerSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;


public class CassandraClient {
	private static StringSerializer serializer = StringSerializer.get();

	public static void main(String[] args) {
		List<ParkingLotInfo> lots = queryParkingLotInfo();
		queryParkingLotStatus(lots);
		//generateSomeTimeUUID();
	}

	private static List<ParkingLotInfo> queryParkingLotInfo() {
		String zoneId = "40005_-105270";
		ParkingLotInfoService service = new ParkingLotInfoService();
		List<ParkingLotInfo> parkingLots = service.getParklingLotInfoByZoneId(zoneId);
		
		System.out.println(String.format("Following Parking Lots are found for '%s' zone", zoneId));
		System.out.println("**********************************************************");
		for (ParkingLotInfo lotInfo : parkingLots) {
			String lotStr = String.format("%1d\t%2s\t%3f\t%4f", 
					lotInfo.getLotId(),
					lotInfo.getType(),
					lotInfo.getLatitude(),
					lotInfo.getLongitude());
			System.out.println(lotStr);
		}
		return parkingLots;
	}

	private static void queryParkingLotStatus(List<ParkingLotInfo> parkingLots) {
		ParkingLotStatusService service = new ParkingLotStatusService();
		service.updateParkingLotStatus(parkingLots);
		
		System.out.println();
		System.out.println("Status of parking lots is as follows");
		System.out.println("*************************************");

		for (ParkingLotInfo lotInfo : parkingLots) {
			String lotStr = String.format("%1d\t%2d", 
					lotInfo.getLotId(),
					lotInfo.getAvailableSpace());
			System.out.println(lotStr);
		}
	}
	
	private static void populateParkingLot() {
		try {
			Cluster cluster = HFactory.getOrCreateCluster("Test Cluster", new CassandraHostConfigurator("localhost:9160"));
			Keyspace keyspace = HFactory.createKeyspace("Keyspace1", cluster);
			Mutator<Integer> mutator = HFactory.createMutator(keyspace, IntegerSerializer.get());

			mutator.insert(146, "ParkingLot", HFactory.createStringColumn("Type", "ADA"));
			mutator.addInsertion(146, "ParkingLot", HFactory.createStringColumn("Type", "ADA"));
			mutator.addInsertion(146, "ParkingLot", HFactory.createStringColumn("Latitude", "100.8"));
			mutator.addInsertion(146, "ParkingLot", HFactory.createStringColumn("Longitude", "86.9"));

			mutator.addInsertion(147, "ParkingLot", HFactory.createStringColumn("Type", "RG"));
			mutator.addInsertion(147, "ParkingLot", HFactory.createStringColumn("Latitude", "102.5"));
			mutator.addInsertion(147, "ParkingLot", HFactory.createStringColumn("Longitude", "77.4"));
			
			mutator.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void basicTest() {
		Cluster cluster = HFactory.getOrCreateCluster("Test Cluster", new CassandraHostConfigurator("localhost:9160"));
		Keyspace keyspace = HFactory.createKeyspace("Keyspace1", cluster);
		
		ColumnQuery<String, String, String> columnQuery = HFactory.createStringColumnQuery(keyspace);
		columnQuery.setColumnFamily("Users").setKey("jsmith").setName("first");
		
		QueryResult<HColumn<String, String>> result = columnQuery.execute();
		System.out.println("The value is:" + result.get().getValue());
	}
	
	private static void generateSomeTimeUUID() {
		Date d1 = new Date(2007, 10, 1);
		Date d2 = new Date(2010, 10, 1);
		Date d3 = new Date(2009, 10, 1);
		UUID u1 = uuidForDate(d1);
		UUID u2 = uuidForDate(d2);
		UUID u3 = uuidForDate(d3);
		
		System.out.println(u1.toString());
		System.out.println(u2.toString());
		System.out.println(u3.toString());
	}
	
	private static UUID uuidForDate(Date d)
    {
		/*
		Magic number obtained from #cassandra's thobbs, who
		claims to have stolen it from a Python library.
		*/
        final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;

        long origTime = d.getTime();
        long time = origTime * 10000 + NUM_100NS_INTERVALS_SINCE_UUID_EPOCH;
        long timeLow = time &       0xffffffffL;
        long timeMid = time &   0xffff00000000L;
        long timeHi = time & 0xfff000000000000L;
        long upperLong = (timeLow << 32) | (timeMid >> 16) | (1 << 12) | (timeHi >> 48) ;
        return new java.util.UUID(upperLong, 0xC000000000000000L);
    }
}
