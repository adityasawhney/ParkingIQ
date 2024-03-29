package com.example.helloandroid;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import com.example.helloandroid.server.*;
import com.example.helloandroid.task.*;

public class HelloAndroid extends Activity {
	private TextView txtTime = null;
	
	public TextView getTxtTime() {
		return this.txtTime;
	}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     // keep the handle to the TextView for later
		this.txtTime = (TextView) findViewById(R.id.txtTime);

		// add a click event handler for the button
		final Button btnCallWebService = (Button) findViewById(R.id.btnCallWebService);
		btnCallWebService.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				//CallWebServiceTask task = new CallWebServiceTask();
				//task.applicationContext = HelloAndroid.this;

				final String piqServerUrl = "http://192.168.64.189:48000";
				LocateParkingLotsTask task = new LocateParkingLotsTask(piqServerUrl);
				task.execute("40.00561", "-105.2705");
			}
		});
    }
    
    public static String UnixTimeStampToDateTime(String unixTimeStamp) {

		long tiemstamp = Long.parseLong(unixTimeStamp);
		String dateStr = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (tiemstamp*1000));

		return dateStr;
	}

	public static String parseJSONResponse(String jsonResponse) {
		String timestamp = "";

		JSONObject json;
		try {
			json = new JSONObject(jsonResponse);
			JSONObject result = json.getJSONObject("Result");
			timestamp = result.getString("Timestamp");

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return timestamp;
	}

	public static String getTimeStampFromYahooService() {

		String responseString = null;

		String baseurlString = "http://developer.yahooapis.com/TimeService/V1/getTime";

		RestClient client = new RestClient(baseurlString);
		client.AddParam("appid", "YahooDemo");
		client.AddParam("output", "json");

		try {
			client.Execute(RestClient.Method.GET);
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseString = client.getResponse();

		return responseString;
	}
	
    public class CallWebServiceTask extends AsyncTask<Void, Void, String> {
		private ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			this.dialog = ProgressDialog.show(applicationContext, "Calling", "Time Service...", true);
		}

		@Override
		protected String doInBackground(Void... params) {

			return HelloAndroid.getTimeStampFromYahooService();

		}

		@Override
		protected void onPostExecute(String result) {
			this.dialog.cancel();
			String timestamp = HelloAndroid.parseJSONResponse(result);
			timestamp = HelloAndroid.UnixTimeStampToDateTime(timestamp);
			HelloAndroid.this.getTxtTime().setText(timestamp);
		}
	}
}