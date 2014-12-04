package com.saba.igc.org.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.saba.igc.org.models.SabaProgram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeeklyProgramsFragment extends SabaBaseFragment {
	
	public WeeklyProgramsFragment(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mSabaClient.getWeeklyPrograms(this);
	}
	
	@Override
	public void getJsonObject(String programName, JSONObject response) {
		mProgramsProgressBar.setVisibility(View.GONE);
		if(response == null){
			// display error.
			return;
		}

		try{
			JSONArray upcomingProgramsJson = response.getJSONArray("entry");
			ArrayList<SabaProgram> programs = SabaProgram.fromJSONArray(upcomingProgramsJson);
			Log.d("TotalItems received: ", programs.size()+"");
			addAll(programs);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Delegate the adding to the internal adapter. // most recommended approach... minimize the code... 
	public void addAll(ArrayList<SabaProgram> programs){
		mAdapter.addAll(programs);
	}
}
