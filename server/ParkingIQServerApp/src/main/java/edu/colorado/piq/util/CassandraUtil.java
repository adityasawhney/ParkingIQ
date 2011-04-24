/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.util;

import edu.colorado.piq.model.CassandraConfig;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;

/**
 * Utility class for Cassandra related functionality.
 */
public final class CassandraUtil {
	
	/**
	 * Connect to Cassandra
	 *
	 * @param config the config to use
	 * @return corresponding keyspace which is used for queries
	 */
	public static Keyspace Connect(CassandraConfig config) {
		Cluster cluster = HFactory.getOrCreateCluster(
				config.getCluster(), 
				new CassandraHostConfigurator(String.format(
						"%1s:%2s", 
						config.getHost(),
						config.getPort())));
		return HFactory.createKeyspace(config.getKeySpace(), cluster);
	}
}
