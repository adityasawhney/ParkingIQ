/*
 * ParkingIQ Project.
 *
 * (c) 2011 Akash Agrawal
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.service.impl;

import edu.colorado.piq.model.GeoPoint;
import edu.colorado.piq.model.Zone;
import edu.colorado.piq.service.ZoneDetectionService;

/**
 * Implementation of ZoneDetectionService interface.
 */
public class ZoneDetectionServiceImpl implements ZoneDetectionService {
	
	/* (non-Javadoc)
	 * @see edu.colorado.piq.service.ZoneDetectionService#identifyZone(edu.colorado.piq.model.GeoPoint)
	 */
	public Zone identifyZone(GeoPoint location) {
		// TODO Code for this will come from Akash
		return new Zone("40001_-105263"); // 40001_-105263(>1) 40005_-105270(=1)
	}
}
