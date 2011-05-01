package com.exapmle.test;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button searchButton = (Button) findViewById(R.id.search1);
        
        searchButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String s1;
                String s2;
                int num=3;
                boolean cont=true;
               // s1= mSearchText.getText().toString();
               // s1=s1.trim();
               //s1= s1.toLowerCase();
                /*if(s1==null | "".equals(s1))
                {
                	
                	Toast.makeText(SelectScreen.this,"no place typed", Toast.LENGTH_SHORT).show();
                }*/
              
                
                {
                Intent i = new Intent(main.this, TwoButtons.class); 
               // i.putExtra(PlacesDbAdapter.KEY_PLACE, s1);
                startActivity(i);
                }
               //to start some activity here on search
             /*   Intent mIntent = new Intent();
                mIntent.putExtras(bundle);
               startActivity(mIntent);
               
               */
            }

        });
        
        
        
        
        
        
        
    }
}