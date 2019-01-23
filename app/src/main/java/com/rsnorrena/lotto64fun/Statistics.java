package com.rsnorrena.lotto64fun;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Statistics extends Activity implements OnClickListener {

	TextView CNetPayout, CTicketCost, CTicketCount, DNetPayout, DTicketCost,
			DTicketCount, LNetPayout, LTicketCost, LTicketCount;

	double CurrentdisplaypayoutATC, Currentticketcost, DailydisplaypayoutATC,
			Dailyticketcost, LifetodatedisplaypayoutATC, Lifetodateticketcost;
	int Currentnumberoftickets, Dailynumberoftickets,
			Lifetodatenumberoftickets;
	Button PlayAgain, ResetStats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats);

		initialize();

		NumberFormat localNumberFormat;
		String str4;
		String str5;
		String str6;

		Bundle localBundle = getIntent().getExtras();
		if (localBundle != null) {

			// retrieve stat data from bundle
			this.Currentnumberoftickets = localBundle
					.getInt("Dcurrentnumberoftickets");
			this.Currentticketcost = localBundle
					.getDouble("Dcurrentticketcost");
			this.CurrentdisplaypayoutATC = localBundle
					.getDouble("DcurrentdisplaypayoutATC");
			this.Dailynumberoftickets = localBundle
					.getInt("Ddailynumberoftickets");
			this.Dailyticketcost = localBundle.getDouble("Ddailyticketscost");
			this.DailydisplaypayoutATC = localBundle
					.getDouble("DdailydisplaypayoutATC");
			this.Lifetodatenumberoftickets = localBundle
					.getInt("Dlifetodatenumberoftickets");
			this.Lifetodateticketcost = localBundle
					.getDouble("Dlifetodateticketcost");
			this.LifetodatedisplaypayoutATC = localBundle
					.getDouble("DlifetodatedisplaypayoutATC");

			// set textfields for tickets counts
			this.CTicketCount.setText(String
					.valueOf(this.Currentnumberoftickets));
			this.DTicketCount
					.setText(String.valueOf(this.Dailynumberoftickets));
			this.LTicketCount.setText(String
					.valueOf(this.Lifetodatenumberoftickets));

			localNumberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);

			// set textfields for cost of tickets in currency format
			String str1 = localNumberFormat.format(new BigDecimal(
					this.Currentticketcost));
			this.CTicketCost.setText(str1);

			String str2 = localNumberFormat.format(new BigDecimal(
					this.Dailyticketcost));
			this.DTicketCost.setText(str2);

			String str3 = localNumberFormat.format(new BigDecimal(
					this.Lifetodateticketcost));
			this.LTicketCost.setText(str3);

			// set payout variables in red font if negative
			str4 = localNumberFormat.format(new BigDecimal(
					this.CurrentdisplaypayoutATC));
			Double x = Double.valueOf(CurrentdisplaypayoutATC);
			if (x > 0.0D) {
				CNetPayout.setText(str4);
			} else {
				CNetPayout.setTextColor(Color.parseColor("#FF0000"));
				CNetPayout.setText(str4);
			}

			str5 = localNumberFormat.format(new BigDecimal(
					this.DailydisplaypayoutATC));
			Double y = Double.valueOf(DailydisplaypayoutATC);
			if (y > 0.0D) {
				DNetPayout.setText(str5);
			} else {
				DNetPayout.setTextColor(Color.parseColor("#FF0000"));
				DNetPayout.setText(str5);
			}

			str6 = localNumberFormat.format(new BigDecimal(
					this.LifetodatedisplaypayoutATC));
			Double z = Double.valueOf(LifetodatedisplaypayoutATC);
			if (z > 0.0D) {
				LNetPayout.setText(str6);
			} else {
				LNetPayout.setTextColor(Color.parseColor("#FF0000"));
				LNetPayout.setText(str6);
			}

		}
	}

	private void initialize() {
		ResetStats = (Button) findViewById(R.id.bStatReset);
		ResetStats.setOnClickListener(this);
		PlayAgain = (Button) findViewById(R.id.bStatPlayAgain);
		PlayAgain.setOnClickListener(this);
		CTicketCount = (TextView) findViewById(R.id.tvCStatTicketCount);
		CTicketCost = (TextView) findViewById(R.id.tvCStatTicketCost);
		CNetPayout = (TextView) findViewById(R.id.tvCStatNetPayout);
		DTicketCount = (TextView) findViewById(R.id.tvDStatTicketCount);
		DTicketCost = (TextView) findViewById(R.id.tvDStatTicketCost);
		DNetPayout = (TextView) findViewById(R.id.tvDStatNetPayout);
		LTicketCount = (TextView) findViewById(R.id.tvLStatTicketCount);
		LTicketCost = (TextView) findViewById(R.id.tvLStatTicketCost);
		LNetPayout = (TextView) findViewById(R.id.tvLStatNetPayout);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.bStatReset:

			ColorStateList localColorStateList = this.CTicketCount
					.getTextColors();
			this.CTicketCount.setText("0");
			this.CTicketCost.setText("0");
			this.CNetPayout.setTextColor(localColorStateList);
			this.CNetPayout.setText("0");
			this.DTicketCount.setText("0");
			this.DTicketCost.setText("0");
			this.DNetPayout.setTextColor(localColorStateList);
			this.DNetPayout.setText("0");
			this.LTicketCount.setText("0");
			this.LTicketCost.setText("0");
			this.LNetPayout.setTextColor(localColorStateList);
			this.LNetPayout.setText("0");

			TinyDB localTinyDB1 = new TinyDB(this);
			localTinyDB1.putInt("TinyDBDailyGamesPlayed", 0);
			localTinyDB1.putDouble("TinyDBDailyTicketCost", 0.0D);
			localTinyDB1.putDouble("TinyDBDailyPayout", 0.0D);

			localTinyDB1.putInt("TinyDBLifeToDateGamesPlayed", 0);
			localTinyDB1.putDouble("TinyDBLifeToDateTicketCost", 0.0D);
			localTinyDB1.putDouble("TinyDBLifeToDatePayout", 0.0D);

			break;

		case R.id.bStatPlayAgain:

			startActivity(new Intent(this, MainActivity.class));
			finish();

			break;
		}

	}

}
