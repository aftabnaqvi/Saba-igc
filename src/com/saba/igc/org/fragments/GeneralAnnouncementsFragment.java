package com.saba.igc.org.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.saba.igc.org.models.SabaProgram;

public class GeneralAnnouncementsFragment extends SabaBaseFragment {
	
	public GeneralAnnouncementsFragment(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mSabaClient.getGeneralAnnouncements(this);
	}
	
//	@Override
//	public void getJsonObject(String programName, JSONObject response) {
//		mProgramsProgressBar.setVisibility(View.GONE);
//		if(response == null){
//			// display error.
//			return;
//		}
//
//		try{
//			JSONArray upcomingProgramsJson = response.getJSONArray("entry");
//			ArrayList<SabaProgram> programs = SabaProgram.fromJSONArray(programName, upcomingProgramsJson);
//			Log.d("TotalItems received: ", programs.size()+"");
//			addAll(programs);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}