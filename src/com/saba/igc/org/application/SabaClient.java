package com.saba.igc.org.application;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.saba.igc.org.activities.SabaServerResponseListener;

public class SabaClient {
	private static SabaClient sabaClient;
	private static Context mContext;
	
	private static final String SABA_BASE_URL = "http://www.saba-igc.org/mobileapp/datafeedproxy.php?sheetName=weekly&sheetId=";
	private static final int mTimeout = 30000;
	
	public synchronized static SabaClient getInstance(Context context) {
	   if(sabaClient == null) {
		   mContext = context;
		   sabaClient = new SabaClient();
	   }

	   return sabaClient;
	}
	
	public void getUpcomingPrograms(final SabaServerResponseListener targert){
		// sheet # 2 is Upcoming programs
		sendRequest(SABA_BASE_URL+2, targert);
 	}
	
	public void getWeeklyPrograms(final SabaServerResponseListener targert){
		// sheet # 4 is Weekly Announcements
		sendRequest(SABA_BASE_URL+4, targert);

	}
	
	public void getCommunityAnnouncements(final SabaServerResponseListener targert){
		// sheet # 5 is Community Announcements
		sendRequest(SABA_BASE_URL+5, targert);

	}
	
	public void getGeneralAnnouncements(final SabaServerResponseListener targert){
		// sheet # 6 is General Announcements
		sendRequest(SABA_BASE_URL+6, targert);
	}
	
	private void sendRequest(final String url, final SabaServerResponseListener targert){
		// create the network client
    	AsyncHttpClient client = new AsyncHttpClient();
    	client.setTimeout(mTimeout);
    	
    	// trigger the network request
    	client.get(url, new JsonHttpResponseHandler(){
    		@Override
    		public void onFailure(int statusCode, Header[] headers,
    				Throwable throwable, JSONObject errorResponse) {
    			// TODO Auto-generated method stub
    			super.onFailure(statusCode, headers, throwable, errorResponse);
    			
    			Log.d("Request: ", throwable.toString());
    			// passing error back to caller.
    			targert.getJsonObject(null, errorResponse);
    		}
    		
    		@Override
    		public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
    			// passing response to caller.
				targert.getJsonObject(null, response);
    		}
    	});
	}
}