/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.model;


/**
 * Represents a zone/region/area which contain the parking lots.
 */
public class Zone {
	
	/** The zone id. */
	private String zoneId;
	
	/**
	 * Instantiates a new zone.
	 *
	 * @param zoneId the zone id
	 */
	public Zone(String zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * Gets the zone id.
	 *
	 * @return the zone id
	 */
	public String getZoneId() {
		return zoneId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Zone with id:%s", this.zoneId);
	}
}
