package com.saba.igc.org.fragments;

import android.os.Bundle;

import com.saba.igc.org.models.SabaProgram;

/**
 * @author Syed Aftab Naqvi
 * @create December, 2014
 * @version 1.0
 */
public class UpcomingProgramsFragment extends SabaBaseFragment {
	private final String PROGRAM_NAME = "Upcoming Programs";

	public UpcomingProgramsFragment(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// get programs from database. if program exists then display. otherwise make a network request.  
		mPrograms =  SabaProgram.getSabaPrograms(PROGRAM_NAME);
		if(mPrograms != null && mPrograms.size() == 0){
			// make a network request to pull the data from server.
			mSabaClient.getUpcomingPrograms(this);
		} 
	}
	
	@Override
	protected void populatePrograms() {
		// TODO Auto-generated method stub
		mAdapter.clear();
		mSabaClient.getUpcomingPrograms(this);
	}
}


