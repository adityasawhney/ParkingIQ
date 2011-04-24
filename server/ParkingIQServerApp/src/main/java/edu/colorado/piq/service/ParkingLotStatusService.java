/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.service;

import java.util.List;

import edu.colorado.piq.model.ParkingLotInfo;

/**
 * The Interface of service to get the current status of lots.
 */
public interface ParkingLotStatusService {
	
	/**
	 * Update parking lot status.
	 *
	 * @param parkingLots the parking lots to look at
	 */
	public abstract void updateParkingLotStatus(List<ParkingLotInfo> parkingLots);
}