/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.service;

import java.util.List;

import edu.colorado.piq.model.ParkingLotInfo;
import edu.colorado.piq.model.Zone;

/**
 * The Interface of service to get all the lots in the zone.
 */
public interface ParkingLotInfoService {
	
	/**
	 * Gets the parking lot info.
	 *
	 * @param zone the zone to look at
	 * @return corresponding parking lot info
	 */
	public abstract List<ParkingLotInfo> getParkingLotInfo(Zone zone);
	
	/**
	 * Gets the parking lot info.
	 *
	 * @param lotId the lot id to look at
	 * @return corresponding parking lot info
	 */
	public abstract ParkingLotInfo getParkingLotInfo(int lotId);
}