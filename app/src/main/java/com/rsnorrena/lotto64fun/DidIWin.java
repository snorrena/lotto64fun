package com.rsnorrena.lotto64fun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class DidIWin extends Activity implements OnClickListener {
	
	double CurrentdisplaypayoutATC, Currentticketcost, DailydisplaypayoutATC, Dailyticketscost, LifetodatedisplaypayoutATC, Lifetodateticketcost;
	int Currentnumberoftickets, Dailynumberoftickets, Lifetodatenumberoftickets;
	String Dailycount, Dailynetpay;
	String SixOfSixMatched, SixOfSixPayout;
	
	String FiveOfSixMatched, FiveOfSixPayout, FiveOfSixPlusBonusMatched, FiveOfSixPlusBonusPayout, FourOfSixMatched, FourOfSixPayout, ThreeOfSixMatched, ThreeOfSixPayout, TwoOfSixPlusBonusMatched, TwoOfSixPlusBonusPayout, Ltdcount, Ltdnetpay, Ltdticketcost, PassedWinningNumbers, TotalWinningAmount, WinnerLoser, WinningAmount;
	
	
	TextView Payout, PlayedNumbers, TotalPayout, WinLose, currentcount, currentdisplaypayoutATC, currentnetpay, currentnumberoftickets, currentticketcost, fiveofsixmatched, fiveofsixpayout, fiveofsixplusbonusmatched, fiveofsixplusbonuspayout, fourofsixmatched, fourofsixpayout, sixofsixmatched, sixofsixpayout, threeofsixmatched, threeofsixpayout, twoofsixplusbonusmatched, twoofsixplusbonuspayout;
	
	
	Button BDPlayAgain, BDStats;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.didiwin);


		
		initialize();
		
		Bundle localBundle = getIntent().getExtras();
		
		
		if (localBundle != null) {
			NumberFormat localNumberFormat = NumberFormat
					.getCurrencyInstance(Locale.CANADA);
			this.WinnerLoser = localBundle.getString("win");
			this.PassedWinningNumbers = localBundle.getString("playednumber");
			this.WinningAmount = localBundle
					.getString("payoutbeforeticketcost");
			this.TotalWinningAmount = localBundle.getString("totalwinsamount");
			
			this.WinLose.setText(this.WinnerLoser);
			this.PlayedNumbers.setText(this.PassedWinningNumbers);
			this.Payout.setText(this.WinningAmount);
			
			this.SixOfSixMatched = localBundle.getString("6of6matched");
			
			this.FiveOfSixPlusBonusMatched = localBundle
					.getString("5of6plusbonusmatched");
			this.FiveOfSixMatched = localBundle.getString("5of6matched");
			this.FourOfSixMatched = localBundle.getString("4of6matched");
			this.ThreeOfSixMatched = localBundle.getString("3of6matched");
			this.TwoOfSixPlusBonusMatched = localBundle
					.getString("2of6plusbonusmatched");
			
			this.sixofsixmatched.setText(this.SixOfSixMatched);
			this.fiveofsixplusbonusmatched
					.setText(this.FiveOfSixPlusBonusMatched);
			this.fiveofsixmatched.setText(this.FiveOfSixMatched);
			this.fourofsixmatched.setText(this.FourOfSixMatched);
			this.threeofsixmatched.setText(this.ThreeOfSixMatched);
			this.twoofsixplusbonusmatched
					.setText(this.TwoOfSixPlusBonusMatched);
			this.SixOfSixPayout = localBundle.getString("6of6matchedpayout");
			this.FiveOfSixPlusBonusPayout = localBundle
					.getString("5of6plusbonusmatchedpayout");
			this.FiveOfSixPayout = localBundle.getString("5of6matchedpayout");
			this.FourOfSixPayout = localBundle.getString("4of6matchedpayout");
			this.ThreeOfSixPayout = localBundle.getString("3of6matchedpayout");
			this.TwoOfSixPlusBonusPayout = localBundle
					.getString("2of6plusbonusmatchedpayout");
			
			if (Integer.valueOf(SixOfSixMatched) > 0 && Double.valueOf(SixOfSixPayout) == 0.0D){
				sixofsixpayout.setTextColor(Color.parseColor("#FF0000"));
				sixofsixpayout.setText("Carried Over");
		}else{
				String str3 = localNumberFormat.format(new BigDecimal(
						this.SixOfSixPayout));
				
				sixofsixpayout.setText(str3);
			}
			
			
			
			String str4 = localNumberFormat.format(new BigDecimal(
					this.FiveOfSixPlusBonusPayout));
			this.fiveofsixplusbonuspayout
					.setText(str4);
			
			String str5 = localNumberFormat.format(new BigDecimal(
					this.FiveOfSixPayout));
			this.fiveofsixpayout.setText(str5);
			
			String str6 = localNumberFormat.format(new BigDecimal(
					this.FourOfSixPayout));
			this.fourofsixpayout.setText(str6);
			
			String str7 = localNumberFormat.format(new BigDecimal(
					this.ThreeOfSixPayout));
			this.threeofsixpayout.setText(str7);
			
			String str8 = localNumberFormat.format(new BigDecimal(
					this.TwoOfSixPlusBonusPayout));
			this.twoofsixplusbonuspayout.setText(str8);
			
			this.Currentnumberoftickets = localBundle
					.getInt("MAcurrentnumberoftickets");
			this.Currentticketcost = localBundle
					.getDouble("MAcurrentticketcost");
			this.CurrentdisplaypayoutATC = localBundle
					.getDouble("MAcurrentdisplaypayoutATC");
			this.Dailynumberoftickets = localBundle
					.getInt("MAdailynumberoftickets");
			this.Dailyticketscost = localBundle.getDouble("MAdailyticketscost");
			this.DailydisplaypayoutATC = localBundle
					.getDouble("MAdailydisplaypayoutATC");
			this.Lifetodatenumberoftickets = localBundle
					.getInt("MAlifetodatenumberoftickets");
			this.Lifetodateticketcost = localBundle
					.getDouble("MAlifetodateticketcost");
			this.LifetodatedisplaypayoutATC = localBundle
					.getDouble("MAlifetodatedisplaypayoutATC");
			this.currentcount.setText(String
					.valueOf(this.Currentnumberoftickets));
			
			String str1 = localNumberFormat.format(new BigDecimal(
					this.Currentticketcost));
			this.currentticketcost.setText(str1);
			
			String str2 = localNumberFormat.format(new BigDecimal(
					this.CurrentdisplaypayoutATC));
			if (CurrentdisplaypayoutATC > 0.0D) {
				currentnetpay.setText(str2);
			}else{
				currentnetpay.setTextColor(Color.parseColor("#FF0000"));
				currentnetpay.setText(str2);
			}
		}
			
	}

	private void initialize() {
		WinLose = (TextView) findViewById(R.id.tvWinLose);
		PlayedNumbers = (TextView) findViewById(R.id.tvPlayedNumber);
		Payout = (TextView) findViewById(R.id.tvPayout);
		sixofsixmatched = (TextView) findViewById(R.id.tv6of6matched);
		fiveofsixplusbonusmatched = (TextView) findViewById(R.id.tv5of6plusbonusmatched);
		fiveofsixmatched = (TextView) findViewById(R.id.tv5of6matched);
		fourofsixmatched = (TextView) findViewById(R.id.tv4of6matched);
		threeofsixmatched = (TextView) findViewById(R.id.tv3of6matched);
		twoofsixplusbonusmatched = (TextView) findViewById(R.id.tv2of6plusbonusmatched);
		sixofsixpayout = (TextView) findViewById(R.id.tv6of6payout);
		fiveofsixplusbonuspayout = (TextView) findViewById(R.id.tv5of6plusbonuspayout);
		fiveofsixpayout = (TextView) findViewById(R.id.tv5of6payout);
		fourofsixpayout = (TextView) findViewById(R.id.tv4of6payout);
		threeofsixpayout = (TextView) findViewById(R.id.tv3of6payout);
		twoofsixplusbonuspayout = (TextView) findViewById(R.id.tv2of6plusbonuspayout);
		currentcount = (TextView) findViewById(R.id.tvCTCn);
		currentticketcost = (TextView) findViewById(R.id.tvCTCo);
		currentnetpay = (TextView) findViewById(R.id.tvCNP);
		
		BDPlayAgain = (Button) findViewById(R.id.bDPlayAgain);
		BDPlayAgain.setOnClickListener(this);

		BDStats = (Button) findViewById(R.id.bDStats);
		BDStats.setOnClickListener(this);
	}

	

	public void onClick(View v) {
		
		switch (v.getId()) {
		
		
		case R.id.bDStats:
			
			Intent localIntent = new Intent(this, Statistics.class);
			Bundle localBundle = new Bundle();
			localBundle.putInt("Dcurrentnumberoftickets",
					this.Currentnumberoftickets);
			localBundle.putDouble("Dcurrentticketcost", this.Currentticketcost);
			localBundle.putDouble("DcurrentdisplaypayoutATC",
					this.CurrentdisplaypayoutATC);
			localBundle.putInt("Ddailynumberoftickets",
					this.Dailynumberoftickets);
			localBundle.putDouble("Ddailyticketscost", this.Dailyticketscost);
			localBundle.putDouble("DdailydisplaypayoutATC",
					this.DailydisplaypayoutATC);
			localBundle.putInt("Dlifetodatenumberoftickets",
					this.Lifetodatenumberoftickets);
			localBundle.putDouble("Dlifetodateticketcost",
					this.Lifetodateticketcost);
			localBundle.putDouble("DlifetodatedisplaypayoutATC",
					this.LifetodatedisplaypayoutATC);
			localIntent.putExtras(localBundle);
			startActivity(localIntent);
			finish();
			break;
			
		case R.id.bDPlayAgain:
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}
		
	}
	public void onBackPressed() {
	}
	
}

