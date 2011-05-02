package edu.colorado.piq;

import com.google.android.maps.MapActivity;
import com.google.android.maps.OverlayItem;

public abstract class ParkingIQBaseMapView extends MapActivity {
	public abstract void showOverlay(OverlayItem[] overlayItems);
}
