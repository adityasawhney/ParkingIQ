package edu.colorado.piq.model.report;

import java.util.HashMap;

import edu.colorado.piq.model.report.DayReport.Session;

public class WeekReport {
	private HashMap<Day, DayReport> weekReports = new HashMap<Day, DayReport>();

	public enum Day {
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY;
		
		public static Day parseDay(String dayString) {
			Day day = Day.MONDAY;
			if (dayString.equalsIgnoreCase("tuesday")) day = Day.TUESDAY;
			else if (dayString.equalsIgnoreCase("wednesday")) day = Day.WEDNESDAY;
			else if (dayString.equalsIgnoreCase("thursday")) day = Day.THURSDAY;
			else if (dayString.equalsIgnoreCase("friday")) day = Day.FRIDAY;
			return day;
		}
	}

	public void addDayReport(Day day, DayReport report) {
		this.weekReports.put(day, report);
	}

	public void addDayReport(Day day, Session session, int spaceCount) {
		if (this.weekReports.get(day) == null) {
			this.weekReports.put(day, new DayReport());
		}
		this.weekReports.get(day).setSpaceCount(session, spaceCount);
	}
	
	public HashMap<Day, DayReport> getWeekReports() {
		return weekReports;
	}
}
