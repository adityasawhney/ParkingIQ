/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package com.example.helloandroid.server;

import java.util.List;

/**
 * The Interface to talk to ParkingIQ server.
 */
public interface ParkingIQClient {
	
	/**
	 * Search parking lots near the given location.
	 *
	 * @param latitude the latitude of current location
	 * @param longitude the longitude of current location
	 * @return corresponding parking lots found
	 */
	List<ParkingLotInfo> searchParkingLots(float latitude, float longitude);
}
