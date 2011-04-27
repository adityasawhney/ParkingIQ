package edu.colorado.piq.model.report;

import java.util.HashMap;

public class QuarterReport {
	private HashMap<Integer, DayReport> lotReport = new HashMap<Integer, DayReport>();
	
	public void addLotReport(int lotId, DayReport report) {
		this.lotReport.put(lotId, report);
	}

	public HashMap<Integer, DayReport> getLotReport() {
		return lotReport;
	}
}
