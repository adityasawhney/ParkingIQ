/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.model;

/**
 * Encapsulates all the configuration parameters related to Cassandra.
 */
public class CassandraConfig {
	
	/** The cluster name. */
	private String cluster;
	
	/** The key space name. */
	private String keySpace;
	
	/** The host I/P. */
	private String host;
	
	/** The port to connect to. */
	private String port;

	/**
	 * Instantiates a new cassandra config.
	 *
	 * @param host the host
	 * @param port the port
	 * @param cluster the cluster
	 * @param keySpace the key space
	 */
	public CassandraConfig(
			String host,
			String port,
			String cluster, 
			String keySpace) {
		this.host = host;
		this.port = port;
		this.cluster = cluster;
		this.keySpace = keySpace;
	}
	
	/**
	 * Gets the cluster name.
	 *
	 * @return the cluster
	 */
	public String getCluster() {
		return cluster;
	}

	/**
	 * Gets the key space name.
	 *
	 * @return the key space
	 */
	public String getKeySpace() {
		return keySpace;
	}

	/**
	 * Gets the host address.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Gets the port to connect to.
	 *
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
}
