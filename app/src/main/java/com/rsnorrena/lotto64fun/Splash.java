package com.rsnorrena.lotto64fun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Splash extends Activity {

	TinyDB localTinyDB1;
	String CurrentDate, SavedDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		CurrentDate = df.format(c.getTime()); 

		localTinyDB1 = new TinyDB(this);
		SavedDate = localTinyDB1.getString("SavedDate");

		//reset the daily total figures to zero  if the saved date does not match the current date.
		if (SavedDate != CurrentDate){
			localTinyDB1.putInt("TinyDBDailyGamesPlayed", 0);
			localTinyDB1.putDouble("TinyDBDailyTicketCost", 0.0D);
			localTinyDB1.putDouble("TinyDBDailyPayout", 0.0D);
		}

		localTinyDB1.putString("SavedDate", CurrentDate);

		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent localIntent = new Intent(Splash.this, About.class);
				Splash.this.startActivity(localIntent);
				Splash.this.finish();
			}
		}, 3000L);
	}
}
