package com.saba.igc.org.fragments;

import android.os.Bundle;

import com.saba.igc.org.models.SabaProgram;

/**
 * @author Syed Aftab Naqvi
 * @create December, 2014
 * @version 1.0
 */
public class WeeklyProgramsFragment extends SabaBaseFragment {
	private final String PROGRAM_NAME = "Weekly Programs";
	
	public WeeklyProgramsFragment(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		// get programs from database. if program exists then display. otherwise make a network request.  
		mPrograms =  SabaProgram.getSabaPrograms(PROGRAM_NAME);
		if(mPrograms.size() == 0)
		{
			// make a network request to pull the data from server.
			mSabaClient.getWeeklyPrograms(this);
		} 
	}
	
	@Override
	protected void populatePrograms() {
		// TODO Auto-generated method stub
		mAdapter.clear();
		mSabaClient.getWeeklyPrograms(this);
	}
}
