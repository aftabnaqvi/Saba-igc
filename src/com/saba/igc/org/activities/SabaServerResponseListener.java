package com.saba.igc.org.activities;
import org.json.JSONObject;

/**
 * @author Syed Aftab Naqvi
 * @create December, 2014
 * @version 1.0
 */
public interface SabaServerResponseListener {
	public void processJsonObject(String programName, JSONObject response);
}
