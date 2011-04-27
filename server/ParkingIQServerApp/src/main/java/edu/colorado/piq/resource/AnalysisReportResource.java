package edu.colorado.piq.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import edu.colorado.piq.Const;
import edu.colorado.piq.model.report.QuarterReport;
import edu.colorado.piq.model.report.WeekReport;
import edu.colorado.piq.service.ParkingLotReportService;

@Path("/reports")
public class AnalysisReportResource {
	private ParkingLotReportService reportService;
	
	public AnalysisReportResource(ParkingLotReportService reportService) {
		this.reportService = reportService;
	}

	@GET
	@Path("/quarterly")
    @Produces("application/json")
    public QuarterReport getQuarterReport(
    		@DefaultValue("") @QueryParam(Const.Param.QUARTER) String quarter) {
		return this.reportService.getQuarterReport(quarter);
	}

	@GET
	@Path("/quarterly/{lotid}")
    @Produces("application/json")
    public WeekReport getQuarterReport(
    		@PathParam(Const.Param.LOT_ID) int lotId,
    		@DefaultValue("") @QueryParam(Const.Param.QUARTER) String quarter) {
		return this.reportService.getQuarterReportByLotId(quarter, lotId);
	}
}
