/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.resource;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import edu.colorado.piq.Const;
import edu.colorado.piq.model.GeoPoint;
import edu.colorado.piq.model.ParkingLotInfo;
import edu.colorado.piq.model.Zone;
import edu.colorado.piq.service.ParkingLotInfoService;
import edu.colorado.piq.service.ParkingLotStatusService;
import edu.colorado.piq.service.ZoneDetectionService;

/**
 * Represents the REST resource for parking lots.
 */
@Path("/parkinglots")
public class ParkingLotResource {
	
	/** Used to identify the zone corresponding to given location. */
	private ZoneDetectionService zoneDetectionService;
	
	/** Used to get all the parking lots in a zone. */
	private ParkingLotInfoService parkingLotInfoService;
	
	/** Used to get the current status of the parking lot(s). */
	private ParkingLotStatusService parkingLotStatusService;
	
	/**
	 * Instantiates a new parking lot resource.
	 *
	 * @param zoneDetectionService the zone detection service
	 * @param parkingLotInfoService the parking lot info service
	 * @param parkingLotStatusService the parking lot status service
	 */
	public ParkingLotResource(
			ZoneDetectionService zoneDetectionService,
			ParkingLotInfoService parkingLotInfoService,
			ParkingLotStatusService parkingLotStatusService) {
		this.zoneDetectionService = zoneDetectionService;
		this.parkingLotInfoService = parkingLotInfoService;
		this.parkingLotStatusService = parkingLotStatusService;
	}

	/**
	 * Get parking lot information for given location.
	 *
	 * @param latitude the latitude of location
	 * @param longitude the longitude of location
	 * @return the list of parking lots which are close to the given location
	 */
	@GET
    @Produces("application/json")
    public List<ParkingLotInfo> getParkingLots(
    		@DefaultValue("0.0") @QueryParam(Const.Param.LATITUDE) float latitude,
    		@DefaultValue("0.0") @QueryParam(Const.Param.LONGITUDE) float longitude) {
		GeoPoint location = new GeoPoint(latitude, longitude);

		// Figure out the zone to which the given belongs
		Zone zone = this.zoneDetectionService.identifyZone(location);
		// Get all the parking lots which lie in the zone
        List<ParkingLotInfo> parkingLots = this.parkingLotInfoService.getParkingLotInfo(zone);
        // Get the current status (available spaces) for each of those lots
        this.parkingLotStatusService.updateParkingLotStatus(parkingLots);
        
        return parkingLots;
    }
}
