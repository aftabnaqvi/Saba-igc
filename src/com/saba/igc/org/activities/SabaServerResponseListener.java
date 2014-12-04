package com.saba.igc.org.activities;
import org.json.JSONObject;

public interface SabaServerResponseListener {
	public void getJsonObject(String programName, JSONObject response);
}
