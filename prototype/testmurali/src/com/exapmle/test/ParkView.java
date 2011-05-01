package com.exapmle.test;

import android.os.Bundle;


import com.google.android.maps.MapActivity;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class ParkView extends MapActivity {
	 private MapView mapView;
	    
	     int latitudeE6 = 37985339;
	   int longitudeE6 = 23716735;
	   int latitudeE7 = 37985339;
	   int longitudeE7 = 23716735;
	   
	   String lat,lon;
	    Bundle extras;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.park_view);
	        
	         extras = getIntent().getExtras();
	        
	        
	         lat=extras.getString(TwoButtons.KEY_LAT);
	         lon=extras.getString(TwoButtons.KEY_LON);
	        
	      	   //Toast.makeText(ParkView.this,"Latitude="+lat+"\nLongitude="+lon, Toast.LENGTH_SHORT).show();

	      	 Float flat = new Float(lat);
	      	 Float flon = new Float(lon);
	      	 
	      	
	      	   //Toast.makeText(ParkView.this,"Latitude="+flat+"\nLongitude="+flon, Toast.LENGTH_SHORT).show();
latitudeE6=(int) (flat*1000000);
longitudeE6=(int) (flon*1000000);
  Toast.makeText(ParkView.this,"Latitude="+latitudeE6+"\nLongitude="+longitudeE6, Toast.LENGTH_SHORT).show();

	        
	        mapView = (MapView) findViewById(R.id.mapview);       
	        mapView.setBuiltInZoomControls(true);
	        
	        List<Overlay> mapOverlays = mapView.getOverlays();
	        Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
	        CustomItemizedOverlay itemizedOverlay = 
	             new CustomItemizedOverlay(drawable, this);
	        
	        GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
	        GeoPoint point2 = new GeoPoint(latitudeE7, longitudeE7);
	        OverlayItem overlayitem = 
	             new OverlayItem(point, "Hello", "I'm in Athens, Greece!");
	        
	        OverlayItem overlayitem2 = 
	             new OverlayItem(point, "Hello", "I'm in Boulder, Colorado!");
	        
	        itemizedOverlay.addOverlay(overlayitem);
	        mapOverlays.add(itemizedOverlay);
	        
	        MapController mapController = mapView.getController();
	        
	        mapController.animateTo(point);
	        mapController.setZoom(6);
	        
	        itemizedOverlay.addOverlay(overlayitem2);
	        mapOverlays.add(itemizedOverlay);
	        
	        MapController mapController2 = mapView.getController();
	        
	        mapController2.animateTo(point);
	        mapController2.setZoom(20);
	        
	    }

	    @Override
	    protected boolean isRouteDisplayed() {
	        return false;
	    }
	    

}
