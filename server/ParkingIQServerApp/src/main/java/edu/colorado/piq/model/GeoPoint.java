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
	private float latitude;
	
	/** The longitude. */
	private float longitude;
	
	/**
	 * Instantiates a new geo point.
	 *
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	public GeoPoint(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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
	
	public boolean isValid() {
		return isValidLatitude() && isValidLongitude();
	}

	public boolean isValidLatitude() {
		return (this.getLatitude() >= -90.0 && this.getLatitude() <= 90.0);
	}

	public boolean isValidLongitude() {
		return (this.getLongitude() >= -180.0 && this.getLongitude() <= 180.0);
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
