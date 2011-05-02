/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq.task;

import java.util.List;

import com.google.android.maps.OverlayItem;

import android.os.AsyncTask;

import edu.colorado.piq.ParkingIQBaseMapView;
import edu.colorado.piq.server.ParkingIQClient;
import edu.colorado.piq.server.ParkingIQClientImpl;
import edu.colorado.piq.server.ParkingLotInfo;

/**
 * Async task to fetch parking lots near a given location.
 */
public class LocateParkingLotsTask extends AsyncTask<String, Void, List<ParkingLotInfo>> {
	
	/** Used to retrieve information from ParkingIQ server. */
	private ParkingIQClient piqClient;
	
	/** The URL where the server is located. */
	private String piqServerUrl;
	
	private ParkingIQBaseMapView view;
	/**
	 * Instantiates a new locate parking lots task.
	 *
	 * @param serverUrl the server url
	 */
	public LocateParkingLotsTask(String serverUrl, ParkingIQBaseMapView view) {
		this.piqServerUrl = serverUrl;
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		this.piqClient = new ParkingIQClientImpl(this.piqServerUrl);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected List<ParkingLotInfo> doInBackground(String... params) {
		List<ParkingLotInfo> lots = null;
		
		if (params.length == 2) {
			float latitude = Float.parseFloat(params[0]);
			float longitude = Float.parseFloat(params[1]);
			lots = this.piqClient.searchParkingLots(latitude, longitude);
		}
		return lots;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(List<ParkingLotInfo> result) {
		OverlayItem[] overlays = ParkingLotOverlays.compose(result);
		this.view.showOverlay(overlays);
	}
}
