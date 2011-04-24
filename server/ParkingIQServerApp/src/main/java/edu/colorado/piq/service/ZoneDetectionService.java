/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.service;

import edu.colorado.piq.model.GeoPoint;
import edu.colorado.piq.model.Zone;

/**
 * The Interface of service to figure out the zone to which a point belongs.
 */
public interface ZoneDetectionService {
	
	/**
	 * Identify zone to which a point belongs.
	 *
	 * @param location the location to look at
	 * @return corresponding zone
	 */
	public abstract Zone identifyZone(GeoPoint location);
}