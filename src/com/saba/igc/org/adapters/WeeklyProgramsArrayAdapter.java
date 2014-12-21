package com.saba.igc.org.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.saba.igc.org.R;
import com.saba.igc.org.extras.EllipsizingTextView;
import com.saba.igc.org.models.SabaProgram;
import com.saba.igc.org.models.DailyProgram;

/**
 * @author Syed Aftab Naqvi
 * @create December, 2014
 * @version 1.0
 */
public class WeeklyProgramsArrayAdapter extends ArrayAdapter<DailyProgram>{

	public WeeklyProgramsArrayAdapter(Context context, List<DailyProgram> objects) {
		super(context, R.layout.upcoming_program_item, objects);
	}
	
	public static class ViewHolder{
		private TextView			tvProgramTitle;
		private EllipsizingTextView	tvProgramDescription;
		private TextView			tvUpatedTime;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// Get the data from position.
		final DailyProgram program = getItem(position);
		
		ViewHolder viewHolder =  null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.upcoming_program_item, parent, false);
			viewHolder.tvProgramTitle = (TextView)convertView.findViewById(R.id.tvProgramTitle);
			viewHolder.tvProgramDescription = (EllipsizingTextView)convertView.findViewById(R.id.tvProgramDescription);

			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
			
		}	
		
		updateVew(viewHolder, program);
		return convertView;
	}
	
	private void updateVew(final ViewHolder viewHolder, DailyProgram program){
		if(viewHolder == null || program == null){
			Log.e("error", "Invalid Arguments");
			return;
		}
		
//		if(viewHolder.tvProgramTitle != null){
//			viewHolder.tvProgramTitle.setText(Html.fromHtml(program.getTitle()));
//		}
//		
//		if(viewHolder.tvProgramDescription != null){
//			Log.d("InAdapter: ", program.getDescription());
//			viewHolder.tvProgramDescription.setText(Html.fromHtml(program.getDescription()));
//			viewHolder.tvProgramDescription.setMovementMethod(LinkMovementMethod.getInstance());
//		}
	}
}

// create a separate adapter for weekly programs:
// don't inherit WeeklyFramgment from  SabaFragmentBase and directly inherit from Fragment. 
// implement everything which we have in SabaFragmentBase in Weekly Fragment.
// think, about 2nd approach and use weekly programs to format the data they we have for other Fragment and detials 
// will show a frm the WeeklyModel.

