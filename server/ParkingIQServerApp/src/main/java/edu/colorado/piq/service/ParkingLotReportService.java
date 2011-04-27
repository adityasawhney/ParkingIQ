package edu.colorado.piq.service;

import edu.colorado.piq.model.report.QuarterReport;
import edu.colorado.piq.model.report.WeekReport;

public interface ParkingLotReportService {
	public QuarterReport getQuarterReport(String quarter);
	public WeekReport getQuarterReportByLotId(String quarter, int lotId);
}
