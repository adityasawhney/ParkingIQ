package com.exapmle.test;







import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwoButtons extends Activity {
	EditText mlatText,mlonText;
	  public static final String KEY_LAT = "lat";
	    public static final String KEY_LON = "lon";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		   setContentView(R.layout.two_buttons);
		 Button currButton = (Button) findViewById(R.id.currloc);
	        Button userButton = (Button) findViewById(R.id.userloc);
	        mlatText = (EditText) findViewById(R.id.lat);
	        mlonText = (EditText) findViewById(R.id.lon);
	        
	        currButton.setOnClickListener(new View.OnClickListener() {

	            public void onClick(View view) {
	                Bundle bundle = new Bundle();
	                String s1;
	                String s2;
	                int num=3;
	                boolean cont=true;
	                
	                
	                Intent i = new Intent(TwoButtons.this, ParkView.class); 
	                 i.putExtra(TwoButtons.KEY_LAT, "");
	                 i.putExtra(TwoButtons.KEY_LON, "");
	                 startActivity(i);
	               /* s1= mSearchText.getText().toString();
	                s1=s1.trim();
	               s1= s1.toLowerCase();
	                if(s1==null | "".equals(s1))
	                {
	                	
	                	Toast.makeText(SelectScreen.this,"no place typed", Toast.LENGTH_SHORT).show();
	                }
	              
	                else
	                {
	                Intent i = new Intent(SelectScreen.this, Displayplaces.class); 
	                i.putExtra(PlacesDbAdapter.KEY_PLACE, s1);
	                startActivity(i);
	                }*/
	               //to start some activity here on search
	             /*   Intent mIntent = new Intent();
	                mIntent.putExtras(bundle);
	               startActivity(mIntent);
	               
	               */
	            }

	        });
	        
	        
	        
	        userButton.setOnClickListener(new View.OnClickListener() {

	            public void onClick(View view) {
	            	
	            	  String lat= mlatText.getText().toString();
	            	  String lon= mlonText.getText().toString();
	            	  Intent i = new Intent(TwoButtons.this, ParkView.class); 
		                 i.putExtra(TwoButtons.KEY_LAT, lat);
		                 i.putExtra(TwoButtons.KEY_LON, lon);
		                 startActivity(i);
	               //to start some activity here on search
	             /*   Intent mIntent = new Intent();
	                mIntent.putExtras(bundle);
	               startActivity(mIntent);
	               
	               */
	            }

	        });
		
		
	}

}
