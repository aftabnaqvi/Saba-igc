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
	
	@Column(name = "updated")
	private String mUpdated;
	
	@Column(name = "programName")
	private String mProgramName;
	
	@Column(name = "title")
	private String mTitle;
	
	//private Content mContent;	
	@Column(name = "content")
	private String mContent;
	
	public String getLastUpdated() {
		return mLastUpdated;
	}
	
	public String getUpdated() {
		return mUpdated;
	}

	public String getProgramName() {
		return mProgramName;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public String  getContent() {
		return mContent;
	}
	
	public void setProgramName(String programName) {
		this.mProgramName = programName;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	
//	public Content getContents() {
//		return mContent;
//	}
	
	public void setContent(String content) {
		this.mContent = content;
	}
	
	public String toString(){
		return "LastUpdated: " + mLastUpdated + "\nUpdated: " + mUpdated + "\nProgramName" + mProgramName + "\nTitle: " + mTitle + "\nContent: " + mContent.toString();
	}
	
	public static SabaProgram fromProgramJSON(JSONObject json){
		SabaProgram upcomingProgram = new SabaProgram();
		
		try {
			upcomingProgram.mLastUpdated = new Date().toString();
			upcomingProgram.mUpdated = json.getString("updated");
			upcomingProgram.mTitle = json.getString("title");
			//String content = json.getString("content");
			//upcomingProgram.mContent = Content.contentFromString(content);
			upcomingProgram.mContent = json.getString("content");
			//upcomingProgram.mContent = json.getString("content");
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
    }
}
