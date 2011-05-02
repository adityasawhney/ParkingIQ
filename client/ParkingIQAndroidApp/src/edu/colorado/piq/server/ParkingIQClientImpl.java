/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.server;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Implementation of ParkingIQClient interface.
 */
public class ParkingIQClientImpl implements ParkingIQClient {
	
	/** Url of the ParkingIQ server. */
	String piqServiceUrl = null;
	
	/**
	 * Instantiates a new parking iq client impl.
	 *
	 * @param serviceUrl the service url
	 */
	public ParkingIQClientImpl(String serviceUrl) {
		this.piqServiceUrl = serviceUrl;
	}
	
	/* (non-Javadoc)
	 * @see com.example.helloandroid.server.ParkingIQClient#searchParkingLots(float, float)
	 */
	public List<ParkingLotInfo> searchParkingLots(float latitude, float longitude) {
		String searchUrl = String.format(
				"http://%1s/parkinglots/?latitude=%2s&longitude=%3s",
				this.piqServiceUrl,
				Float.toString(latitude),
				Float.toString(longitude));
		RestClient client = new RestClient(searchUrl);

		try {
			client.Execute(RestClient.Method.GET);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return compileParkingLotResult(client.getResponse());
	}

	/**
	 * Compile parking lot result.
	 *
	 * @param result the result we got from server in JSON format
	 * @return corresponding data converted into client model
	 */
	private List<ParkingLotInfo> compileParkingLotResult(String result) {
		List<ParkingLotInfo> lots = new LinkedList<ParkingLotInfo>();
		
		if (result == null) return lots;
		try {
			JSONObject jResult = new JSONObject(result);
			if (jResult.optJSONArray("parkingLotInfo") != null) {
				JSONArray jLots = jResult.getJSONArray("parkingLotInfo");
				for (int i = 0; i < jLots.length(); i++) {
					ParkingLotInfo lot = convert(jLots.getJSONObject(i));
					lots.add(lot);
				}
			}
			else if (jResult.optJSONObject("parkingLotInfo") != null) {
				JSONObject jLot = jResult.getJSONObject("parkingLotInfo");
				ParkingLotInfo lot = convert(jLot);
				lots.add(lot);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return lots;
	}
	
	/**
	 * Convert a single parking lot object
	 *
	 * @param jLot the lot infor in JSON
	 * @return corresponding parking lot info
	 * @throws JSONException the jSON exception
	 */
	private ParkingLotInfo convert(JSONObject jLot) throws JSONException {
		return new ParkingLotInfo(
				jLot.getInt("lotId"),
				(float)jLot.getDouble("latitude"),
				(float)jLot.getDouble("longitude"),
				jLot.getInt("availableSpace"));
		
	}
}
