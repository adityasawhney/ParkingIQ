/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.model;


/**
 * Represents the coordinates of a location.
 */
public class GeoPoint {
	
	/** The latitude. */
	private int latitude;
	
	/** The longitude. */
	private int longitude;
	
	/**
	 * Instantiates a new geo point.
	 *
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	public GeoPoint(int latitude, int longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public int getLatitude() {
		return latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public int getLongitude() {
		return longitude;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"GeoPoint with latitude: %1d longitude %2d",
				this.latitude, 
				this.longitude);
	}
}
