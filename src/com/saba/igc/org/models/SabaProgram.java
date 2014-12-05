package com.saba.igc.org.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


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

@Table (name="SabaProgram")
public class SabaProgram extends Model{
	@Column(name = "lastUpdate")
	private String mLastUpdated;
	
	// lastRequestedTime, last 
	@Column(name = "programName")
	private String mProgramName;
	
	@Column(name = "title")
	private String mTitle;
	
	//private Content mContent;	
	@Column(name = "content")
	private String mContent;
	
	public String getUpdated() {
		return mLastUpdated;
	}

	public String getProgramName() {
		return mProgramName;
	}

	public void setProgramName(String programName) {
		this.mProgramName = programName;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	
//	public Content getContents() {
//		return mContent;
//	}
	
	
	public String  getContent() {
		return mContent;
	}
	
	public void setContent(String content) {
		this.mContent = content;
	}
	
	public String toString(){
		return "LastUpdated: " + mLastUpdated + "\nProgramName" + mProgramName + "\nTitle: " + mTitle + "\nContent: " + mContent.toString();
	}
	
	public static SabaProgram fromProgramJSON(JSONObject json){
		SabaProgram upcomingProgram = new SabaProgram();
		
		try {
			upcomingProgram.mLastUpdated = json.getString("updated");
			upcomingProgram.mTitle = json.getString("title");
			//String content = json.getString("content");
			//upcomingProgram.mContent = Content.contentFromString(content);
			upcomingProgram.mContent = json.getString("content");
			upcomingProgram.mContent = json.getString("content");
			//upcomingPrograms.mLink = json.getString("link");
			
			
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
	
	public static ArrayList<SabaProgram> fromJSON(JSONObject completeJson){
		return null;
		
	}
	// Get all times.
    public static List<SabaProgram> getAll() {
        //return new Select().from(Tweet.class).orderBy("uid DESC").execute();
        return new Select().from(SabaProgram.class).execute();
    }
    
    
    // Get todays time. date format should be MM-DD only. we don't care about year here..
    public static List<SabaProgram> getTodayPrayerTimes(String city, String today) {
        return new Select()
        .from(SabaProgram.class)
        .where("city = ? AND date = ?", city, today)
        .execute();
    }
}
