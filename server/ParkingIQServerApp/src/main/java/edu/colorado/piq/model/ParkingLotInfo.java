/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Encapsulates all the information pertaining to a parking lot.
 */
@XmlRootElement
public class ParkingLotInfo {
	
	/** The lot id. */
	private int lotId;
	
	/** The type. */
	private String type;
	
	/** The latitude. */
	private float latitude;
	
	/** The longitude. */
	private float longitude;
	
	/** The available space. */
	private int availableSpace;

	/**
	 * Instantiates a new parking lot info.
	 */
	public ParkingLotInfo() {
		this.availableSpace = -1;
	}

	/**
	 * Instantiates a new parking lot info.
	 *
	 * @param lotId the lot id
	 * @param type the type
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param availableSpace the available space
	 */
	public ParkingLotInfo(
			int lotId,
			String type, 
			float latitude, 
			float longitude,
			int availableSpace) {
		this.lotId = lotId;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.availableSpace = availableSpace;
	}

	/**
	 * Creates the.
	 *
	 * @param lotId the lot id
	 * @param type the type
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @return the parking lot info
	 */
	public static ParkingLotInfo create(
			String lotId,
			String type, 
			String latitude, 
			String longitude)	{
		int id = Integer.parseInt(lotId);
		float lat = Float.parseFloat(latitude);
		float lng = Float.parseFloat(longitude);
		
		return new ParkingLotInfo(id, type, lat, lng, -1);
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
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
	
	/**
	 * Sets the lot id.
	 *
	 * @param lotId the new lot id
	 */
	public void setLotId(int lotId) {
		this.lotId = lotId;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * Sets the available space.
	 *
	 * @param availableSpace the new available space
	 */
	public void setAvailableSpace(int availableSpace) {
		this.availableSpace = availableSpace;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"Parking lot with \nid: %1d\ntype: %2s\nlatitude: %3f\nlongitude: %4f\navailable space: %5d",
				this.getLotId(),
				this.getType(),
				this.getLatitude(),
				this.getLongitude(),
				this.getAvailableSpace());
	}
}
