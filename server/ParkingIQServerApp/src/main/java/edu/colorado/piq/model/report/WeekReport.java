package edu.colorado.piq.model.report;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

import edu.colorado.piq.model.ParkingLotInfo;
import edu.colorado.piq.model.report.DayReport.Session;

@XmlRootElement
public class WeekReport {
	private HashMap<Day, DayReport> weekReports = new HashMap<Day, DayReport>();
	private ParkingLotInfo parkingLotInfo;
	
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

	public void setWeekReports(HashMap<Day, DayReport> weekReports) {
		this.weekReports = weekReports;
	}

	public ParkingLotInfo getParkingLotInfo() {
		return parkingLotInfo;
	}

	public void setParkingLotInfo(ParkingLotInfo parkingLotInfo) {
		this.parkingLotInfo = parkingLotInfo;
	}
}
