package com.saba.igc.org.models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

//{
//    "day": "Tuesday",
//    "englishdate": "'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''December 9",
//    "hijridate": "Safar 16",
//    "time": "",
//    "program": ""
//},
//{
//    "day": "",
//    "englishdate": "",
//    "hijridate": "",
//    "time": " ",
//    "program": "Maghrib Prayer"
//}

//@Table(name = "WeeklyProgram")
public class WeeklyProgram{ //extends Model {
	//@Column(name = "day")
	private String mDay;
	
	//@Column(name = "englishDate")
	private String mEnglishDate;
	
	//@Column(name = "hijiriDate")
	private String mHijriDate;
	
	//@Column(name = "time")
	private String mTime;
	
	//@Column(name = "program")
	private String mProgram;

	public String getDay() {
		return mDay;
	}

	public void setDay(String mDay) {
		this.mDay = mDay;
	}

	public String getEnglishDate() {
		return mEnglishDate;
	}

	public void setEnglishDate(String mEnglishDate) {
		this.mEnglishDate = mEnglishDate;
	}

	public String getHijriDate() {
		return mHijriDate;
	}

	public void setHijriDate(String mHijriDate) {
		this.mHijriDate = mHijriDate;
	}

	public String getTime() {
		return mTime;
	}

	public void setTime(String mTime) {
		this.mTime = mTime;
	}

	public String getProgram() {
		return mProgram;
	}

	public void setProgram(String mProgram) {
		this.mProgram = mProgram;
	}
	
	public static WeeklyProgram fromProgramJSON(JSONObject json){
		WeeklyProgram weeklyProgram = new WeeklyProgram();
		
		try {
			System.out.println("JSON: " + json.toString());
			weeklyProgram.mDay = json.getString("day");
			weeklyProgram.mEnglishDate = json.getString("englishdate").replace("'","");
//			weeklyProgram.mEnglishDate = weeklyProgram.mEnglishDate
			weeklyProgram.mHijriDate = json.getString("hijridate");
			weeklyProgram.mTime = json.getString("time").replace("'","");;
			weeklyProgram.mProgram = json.getString("program");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weeklyProgram;
	}
}
