package com.saba.igc.org.adapters;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.saba.igc.org.R;
import com.saba.igc.org.models.PrayerTimes;

public class PrayerTimesAdapter extends ArrayAdapter<PrayerTimes> {

	public PrayerTimesAdapter(Context context,
			List<PrayerTimes> objects) {
		
		super(context, R.layout.prayer_time_item, objects);
		// TODO Auto-generated constructor stub
	}

}
