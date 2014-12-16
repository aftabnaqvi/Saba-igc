package com.saba.igc.org.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

// first version - coming data
//{
//    "id": "https://spreadsheets.google.com/feeds/list/0Av-PwTFDtwEYdEdnYllyYUxjbnd2VzkwaG9oUExLS0E/2/public/values/wxl8y",
//    "updated": "2014-11-19T22:27:44.790Z",
//    "category": {
//        "@attributes": {
//            "scheme": "http://schemas.google.com/spreadsheets/2006",
//            "term": "http://schemas.google.com/spreadsheets/2006#list"
//        }
//    },
//    "title": "Nadeem Sarwar at SABA",
//    "content": "description: Guest Nauha reciter Nadeem Sarwar will be at SABA on Saturday, November 29 2014</b> to recite Nauha and Marsiya., imageurl: http://www.saba-igc.org/data/weeklyemail/muharram_flag.jpg, imagewidth: 60, imageheight: 60",
//    "link": {
//        "@attributes": {
//            "rel": "self",
//            "type": "application/atom+xml",
//            "href": "http://spreadsheets.google.com/feeds/list/0Av-PwTFDtwEYdEdnYllyYUxjbnd2VzkwaG9oUExLS0E/2/public/values/wxl8y"
//        }
//    }
//}

// 2nd version - coming data
//{
//    "title": "School Timings",
//    "description": "\nBell schedule 2 has now being implemented in Sunday school:\n\nBell Schedule 2 (Approx. November to March)</b>\n\nFirst Period: 11:00 am – 11:50 am\n\nSecond Period:11:50 am – 12:40 pm\n\nSalaat Period:12:40 pm -1:00 pm\n\nThird Period:1:10 pm – 2:00 pm\n\nLUNCH/Pick-up time: 2:00 pm</font>\n",
//    "urltitle": "",
//    "url": "",
//    "imageurl": "http://www.saba-igc.org/data/announcements/email/school.jpeg",
//    "imagewidth": "",
//    "imageheight": ""
//}

/**
 * @author Syed Aftab Naqvi
 * @create December, 2014
 * @version 1.0
 */
@Table (name="SabaProgram")
public class SabaProgram extends Model{

	// lastRequestedTime, last 
	@Column(name = "lastUpdated")
	private String mLastUpdated;
	
	//@Column(name = "updated")
	//private String mUpdated;
	
	@Column(name = "programName")
	private String mProgramName;
	
	@Column(name = "title")
	private String mTitle;
	
	@Column(name = "description")
	private String mDescription;
	
	@Column(name = "imageUrl")
	private String mImageUrl;
	
	@Column(name = "imageHeight")
	private int mImageHeight;
	
	@Column(name = "imageWidth")
	private int mImageWidth;
	
	
	public String getLastUpdated() {
		return mLastUpdated;
	}
	
	public String getImageUrl() {
		return mImageUrl;
	}

	public String getProgramName() {
		return mProgramName;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public String  getDescription() {
		return mDescription;
	}
	
	public void setProgramName(String programName) {
		this.mProgramName = programName;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}
	
	public int getImageHeight() {
		return mImageHeight;
	}
	
	public int getImageWidth() {
		return mImageWidth;
	}
	
	public String toString(){
		return "LastUpdated: " + mLastUpdated + "\nImageUrl: " + mImageUrl + "\nProgramName" + mProgramName + "\nTitle: " + mTitle + "\nDescription: " + mDescription;
	}
	
	public static SabaProgram fromProgramJSON(JSONObject json){
		SabaProgram upcomingProgram = new SabaProgram();
		
		try {
			System.out.println("JSON: " + json.toString());
			upcomingProgram.mLastUpdated = new Date().toString();
			upcomingProgram.mTitle = json.getString("title");
			upcomingProgram.mDescription = json.getString("description");
			
			if(!json.isNull("imageurl")){
				upcomingProgram.mImageUrl = json.getString("imageurl");
				upcomingProgram.mImageHeight = Integer.parseInt(json.getString("imageheight"));
				upcomingProgram.mImageWidth = Integer.parseInt(json.getString("imagewidth"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return upcomingProgram;
	}
	
	public static ArrayList<SabaProgram> fromJSONArray(String programName, JSONArray jsonArray){
		ArrayList<SabaProgram> programs = new ArrayList<SabaProgram>();
		
		for(int i=0; i<jsonArray.length(); i++){
			JSONObject programJson = null;
			try{
				programJson = jsonArray.getJSONObject(i);
			} catch(JSONException e){
				e.printStackTrace();
				continue;
			}
			
			SabaProgram upcomingProgram = SabaProgram.fromProgramJSON(programJson);
			upcomingProgram.setProgramName(programName);
			
			if(upcomingProgram != null){
				Log.d("Program: ", upcomingProgram.toString());
				programs.add(upcomingProgram);
			}
		}
		
		return programs;
	}
	
	public static ArrayList<SabaProgram> weeklyProgramsFromJSONArray(String programName, JSONArray jsonArray){
		ArrayList<SabaProgram> programs = new ArrayList<SabaProgram>();
		
		int index = 0;
		int length = jsonArray.length();
		
		JSONObject programJson = null;
		try{
			programJson = jsonArray.getJSONObject(index);
		} catch(JSONException e){
			e.printStackTrace();
		}
		
		while(index < length){
			SabaProgram sabaProgram = new SabaProgram();
			sabaProgram.setProgramName(programName);
			
			WeeklyProgram weeklyProgram = WeeklyProgram.fromProgramJSON(programJson);
			
			if(weeklyProgram != null){
				StringBuilder sb = new StringBuilder();
				sb.append(weeklyProgram.getDay());
				sb.append("/");
				sb.append(weeklyProgram.getEnglishDate());
				sb.append("/");
				sb.append(weeklyProgram.getHijriDate());
				sabaProgram.mTitle = sb.toString();
				sb.setLength(0);
				do{
					try{
						index++;
						programJson = jsonArray.getJSONObject(index);
					} catch(JSONException e){
						e.printStackTrace();
						continue;
					}
					weeklyProgram = WeeklyProgram.fromProgramJSON(programJson);
					sb.append(weeklyProgram.getTime());
					sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					sb.append(weeklyProgram.getProgram());
					sb.append("<br>");
				} while(jsonArray.length() > index && weeklyProgram.getDay().isEmpty());
				sabaProgram.mDescription = sb.toString();
				Log.d("Weekly - Program: ", sabaProgram.mDescription);
				programs.add(sabaProgram);
			}
		}
		
		return programs;
	}
	
	public static ArrayList<SabaProgram> fromJSON(JSONObject completeJson){
		return null;
		
	}
	
	// Persistence methods.
    public void saveProgram(){
    	this.save();
    }
    
    public static void deleteSabaPrograms(String programName){
    	new Delete()
    	.from(SabaProgram.class)
    	.where("programName = ?", programName)
    	.execute();
    }
    
	// Get all times.
    public static List<SabaProgram> getAll() {
        return new Select().from(SabaProgram.class).execute();
    }
    
    
    // get recent SABA programs by given program name from the database.
    public static List<SabaProgram> getSabaPrograms(String programName) {
        return new Select()
        .from(SabaProgram.class)
        .where("programName = ?", programName)
        .execute();
        
        
        // where lastUpdate = "today"
    }
}
