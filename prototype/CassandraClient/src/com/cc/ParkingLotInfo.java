package com.cc;

public class ParkingLotInfo {
	private int lotId;
	private String type;
	private float latitude;
	private float longitude;
	private int availableSpace;

	public ParkingLotInfo() {
		this.availableSpace = -1;
	}

	public ParkingLotInfo(
			int lotId,
			String type, 
			float latitude, 
			float longitude,
			int availableSpace) {
		this.lotId = lotId;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.availableSpace = availableSpace;
	}

	public static ParkingLotInfo create(
			String lotId,
			String type, 
			String latitude, 
			String longitude)	{
		int id = Integer.parseInt(lotId);
		float lat = Float.parseFloat(latitude);
		float lng = Float.parseFloat(longitude);
		
		return new ParkingLotInfo(id, type, lat, lng, -1);
	}
	
	public int getLotId() {
		return lotId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public void setLotId(int lotId) {
		this.lotId = lotId;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public int getAvailableSpace() {
		return availableSpace;
	}
	
	public void setAvailableSpace(int availableSpace) {
		this.availableSpace = availableSpace;
	}
}
