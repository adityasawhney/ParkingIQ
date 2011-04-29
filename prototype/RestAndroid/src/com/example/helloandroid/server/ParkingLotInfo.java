/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package com.example.helloandroid.server;

/**
 * Represents the information corresponding to a parking lot.
 */
public class ParkingLotInfo {
	
	/** The lot id. */
	private int lotId;
	
	/** The latitude. */
	private float latitude;
	
	/** The longitude. */
	private float longitude;
	
	/** The available space. */
	private int availableSpace;
	
	/**
	 * Instantiates a new parking lot info.
	 *
	 * @param lotId the lot id
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param availableSpace the available space
	 */
	public ParkingLotInfo(int lotId, float latitude, float longitude,
			int availableSpace) {
		this.lotId = lotId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.availableSpace = availableSpace;
	}

	/**
	 * Gets the lot id.
	 *
	 * @return the lot id
	 */
	public int getLotId() {
		return lotId;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Gets the available space.
	 *
	 * @return the available space
	 */
	public int getAvailableSpace() {
		return availableSpace;
	}
}
