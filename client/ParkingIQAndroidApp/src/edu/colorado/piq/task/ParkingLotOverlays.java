package edu.colorado.piq.task;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import edu.colorado.piq.ParkingIQBaseMapView;
import edu.colorado.piq.server.ParkingLotInfo;

public final class ParkingLotOverlays {

	public static void request(ParkingIQBaseMapView view, float lat, float lng) {
		String serverUrl = "172.23.1.66:8000";
		LocateParkingLotsTask task = new LocateParkingLotsTask(serverUrl, view);
		task.execute(Float.toString(lat), Float.toString(lng));
	}
	
	public static OverlayItem[] compose(List<ParkingLotInfo> lots) {
		OverlayItem[] overlayItems = new OverlayItem[lots.size()];
		for (int i = 0; i < lots.size(); i++) {
			int l1 = (int) (lots.get(i).getLatitude() * 1e6);
			int l2 = (int) (lots.get(i).getLongitude() * 1e6);
			overlayItems[i] = new OverlayItem(
					new GeoPoint(l1, l2), 
					Integer.toString(lots.get(i).getLotId()), 
					Integer.toString(lots.get(i).getAvailableSpace()));
		}
		return overlayItems;		
	}
}
