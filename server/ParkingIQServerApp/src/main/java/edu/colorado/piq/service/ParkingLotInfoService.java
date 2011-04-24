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
	 * Gets the parkling lot info.
	 *
	 * @param zone the zone to look at
	 * @return corresponding parkling lot info
	 */
	public abstract List<ParkingLotInfo> getParklingLotInfo(Zone zone);
}