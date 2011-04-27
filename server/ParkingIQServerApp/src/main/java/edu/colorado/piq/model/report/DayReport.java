package edu.colorado.piq.model.report;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DayReport {
	public enum Session {
		MORNING,
		AFTERNOON,
		EVENING;
		
		public static Session parseSession(String sessionStr) {
			Session session = Session.MORNING;
			if (sessionStr.equalsIgnoreCase("afternoon")) session = Session.AFTERNOON;
			else if (sessionStr.equalsIgnoreCase("evening")) session = Session.EVENING;
			return session;
		}
		
	};
	
	private int morningSpaceCount;
	private int afternoonSpaceCount;
	private int eveningSpaceCount;

	public DayReport() {
	}

	public DayReport(int morningSpaceCount, int afternoonSpaceCount,
			int eveningSpaceCount) {
		this.morningSpaceCount = morningSpaceCount;
		this.afternoonSpaceCount = afternoonSpaceCount;
		this.eveningSpaceCount = eveningSpaceCount;
	}

	public int getMorningSpaceCount() {
		return morningSpaceCount;
	}
	
	public int getAfternoonSpaceCount() {
		return afternoonSpaceCount;
	}
	
	public int getEveningSpaceCount() {
		return eveningSpaceCount;
	}

	public void setSpaceCount(Session session, int spaceCount) {
		if (session == Session.MORNING) {
			this.morningSpaceCount = spaceCount;
		}
		else if (session == Session.AFTERNOON) {
			this.afternoonSpaceCount = spaceCount;
		}
		else if (session == Session.EVENING) {
			this.eveningSpaceCount = spaceCount;
		}
	}
}
