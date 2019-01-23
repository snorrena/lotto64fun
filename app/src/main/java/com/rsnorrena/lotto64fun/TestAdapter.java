package com.rsnorrena.lotto64fun;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class TestAdapter {
	protected static final String TAG = "DataAdapter";
	private final Context mContext;
	private SQLiteDatabase mDb;
	private DataBaseHelper mDbHelper;

	public TestAdapter(Context context) {
		this.mContext = context;
		mDbHelper = new DataBaseHelper(mContext);
	}

//	public TestAdapter encrypt() throws SQLException, IOException {
//		this.mDbHelper.encrypt();
//		return this;
//	}

	public TestAdapter createDatabase() throws SQLException {
		try {
			this.mDbHelper.createDataBase();
			return this;
		} catch (IOException localIOException) {
			Log.e("DataAdapter", localIOException.toString()
					+ "  UnableToCreateDatabase");
			throw new Error("UnableToCreateDatabase");
		}
	}

	public TestAdapter open() throws SQLException {
		try {
			this.mDbHelper.openDataBase();
			this.mDbHelper.close();
			this.mDb = this.mDbHelper.getReadableDatabase("@Override");
			return this;
		} catch (SQLException localSQLException) {
			Log.e("DataAdapter", "open >>" + localSQLException.toString());
			throw localSQLException;
		}
	}

	public void close() {
		this.mDbHelper.close();
	}

	// method to receive the validated play numbers array
	public String[] checknumbers(String[] ValidatedEnteredNumbersArray) {
		// TODO Auto-generated method stub

		// initialize variables to hold all return data
		Double SumOfPlayPayout;
		Double SumOfTicketCost;
		Double SumOfJackpotWinAmount;
		Double SumWin5of6plusBonus;
		Double SumWin5of6;
		Double SumWin4of6;
		Double SumWin3of6;
		Double SumWin2of6plusBonus;

		int DatabaseDrawCount;
		int Match6of6Count;
		int Match5of6PlusBonusCount;
		int Match5of6count;
		int Match4of6Count;
		int Match3of6Count;
		int Match2of6plusBonusCount;

		String[] playpayout = new String[18];
		Cursor localCursor;
		String[] arrData;

		// create cursor object to cycle through entire database
		localCursor = this.mDb.query("LottoData", new String[] { "*" }, null,
				null, null, null, null, null);

		DatabaseDrawCount = localCursor.getCount(); // number of draws

		arrData = new String[localCursor.getColumnCount()]; // array based on
															// number of columns

		// these variables need to be reset each run of the numberscheck method
		SumOfPlayPayout = Double.valueOf(0.0D);
		SumOfTicketCost = Double.valueOf(0.0D);
		SumOfJackpotWinAmount = Double.valueOf(0.0D);
		SumWin5of6plusBonus = Double.valueOf(0.0D);
		SumWin5of6 = Double.valueOf(0.0D);
		SumWin4of6 = Double.valueOf(0.0D);
		SumWin3of6 = Double.valueOf(0.0D);
		SumWin2of6plusBonus = Double.valueOf(0.0D);

		Match6of6Count = 0;
		Match5of6PlusBonusCount = 0;
		Match5of6count = 0;
		Match4of6Count = 0;
		Match3of6Count = 0;
		Match2of6plusBonusCount = 0;

		if (localCursor != null) {

			for (localCursor.moveToFirst(); !localCursor.isAfterLast(); localCursor
					.moveToNext()) {

				// pull information from the database and set to array

				arrData[0] = localCursor.getString(0);
				arrData[1] = localCursor.getString(1);
				arrData[2] = localCursor.getString(2);
				arrData[3] = localCursor.getString(3);
				arrData[4] = localCursor.getString(4);
				arrData[5] = localCursor.getString(5);
				arrData[6] = localCursor.getString(6);
				arrData[7] = localCursor.getString(7);
				arrData[8] = localCursor.getString(8);
				arrData[9] = localCursor.getString(9);
				arrData[10] = localCursor.getString(10);
				arrData[11] = localCursor.getString(11);
				arrData[12] = localCursor.getString(12);
				arrData[13] = localCursor.getString(13);
				arrData[14] = localCursor.getString(14);
				arrData[15] = localCursor.getString(15);
				arrData[16] = localCursor.getString(16);

				// initialize variables for the matched number check
				boolean Win = false;
				boolean BonusNumber = false;
				int MatchedNumbers = 0;

				// pull the real ticket cost variable from the database
				Double LocalTicketCost = Double.valueOf(arrData[16]);

				// increment the sum of ticket cost
				SumOfTicketCost = Double.valueOf(SumOfTicketCost.doubleValue()
						+ LocalTicketCost.doubleValue());

				// check for matched numbers and bonus number and set booleans
				for (int i = 2; i < 8; i++) {

					for (int j = 0; j < 6; j++) {
						if (arrData[i].equals(ValidatedEnteredNumbersArray[j])) {
							MatchedNumbers++;
							Win = true;

						}
						if (ValidatedEnteredNumbersArray[j].equals(arrData[8])) {
							BonusNumber = true;
						}
					}
				}

				// main loop to determine play results
				if (Win) {

					// if the matched number is 6
					if (MatchedNumbers == 6) {
						playpayout[0] = arrData[9];
						Match6of6Count++;
						Double LocalPlayWinAmount = Double.valueOf(arrData[9]);
						SumOfJackpotWinAmount = Double
								.valueOf(SumOfJackpotWinAmount.doubleValue()
										+ LocalPlayWinAmount.doubleValue());

						if (Double.valueOf(arrData[9]).doubleValue() == 0.0D) {
							playpayout[16] = "Your ticket was matched to a drawn number, however, there wasn't a winner of the grand prize with these numbers.";
						}

					}

					// if matched number = 5 and the bonus number is picked
					if (MatchedNumbers == 5 && BonusNumber == true) {
						playpayout[0] = arrData[10];
						Match5of6PlusBonusCount++;
						Double LocalSumWin5of6plusBonus = Double
								.valueOf(arrData[10]);
						SumWin5of6plusBonus = Double
								.valueOf(SumWin5of6plusBonus.doubleValue()
										+ LocalSumWin5of6plusBonus
												.doubleValue());

						// matched number count = 5
					} else if (MatchedNumbers == 5) {
						playpayout[0] = arrData[11];
						Match5of6count++;
						Double LocalSumWin5of6 = Double.valueOf(arrData[11]);
						SumWin5of6 = Double.valueOf(SumWin5of6.doubleValue()
								+ LocalSumWin5of6.doubleValue());

						// matched number count = 4
					} else if (MatchedNumbers == 4) {
						playpayout[0] = arrData[12];
						Match4of6Count++;
						Double LocalSumWin4of6 = Double.valueOf(arrData[12]);
						SumWin4of6 = Double.valueOf(SumWin4of6.doubleValue()
								+ LocalSumWin4of6.doubleValue());

						// matched number count = 3
					} else if (MatchedNumbers == 3) {
						playpayout[0] = arrData[13];
						Match3of6Count++;
						Double LocalSumWin3of6 = Double.valueOf(arrData[13]);
						SumWin3of6 = Double.valueOf(SumWin3of6.doubleValue()
								+ LocalSumWin3of6.doubleValue());

						// if the matched number is 2 and the bonus number is
						// picked
					} else if (MatchedNumbers == 2 && BonusNumber == true) {
						playpayout[0] = arrData[14];
						Match2of6plusBonusCount++;
						Double LocalSumWin2of6plusBonus = Double
								.valueOf(arrData[14]);
						SumWin2of6plusBonus = Double
								.valueOf(SumWin2of6plusBonus.doubleValue()
										+ LocalSumWin2of6plusBonus
												.doubleValue());
					}

					// total of all category win amounts
					SumOfPlayPayout = SumOfJackpotWinAmount
							+ SumWin5of6plusBonus + SumWin5of6 + SumWin4of6
							+ SumWin3of6 + SumWin2of6plusBonus;
				}

			}

		}

		localCursor.close();
		this.mDb.close();

		playpayout[0] = String.valueOf(SumOfPlayPayout);
		playpayout[1] = Integer.toString(Match6of6Count);
		playpayout[2] = Integer.toString(Match5of6PlusBonusCount);
		playpayout[3] = Integer.toString(Match5of6count);
		playpayout[4] = Integer.toString(Match4of6Count);
		playpayout[5] = Integer.toString(Match3of6Count);
		playpayout[6] = Integer.toString(Match2of6plusBonusCount);
		playpayout[7] = Integer.toString(DatabaseDrawCount);
		playpayout[8] = String.valueOf(SumOfTicketCost);
		playpayout[9] = String.valueOf(SumOfJackpotWinAmount);
		playpayout[10] = String.valueOf(SumWin5of6plusBonus);
		playpayout[11] = String.valueOf(SumWin5of6);
		playpayout[12] = String.valueOf(SumWin4of6);
		playpayout[13] = String.valueOf(SumWin3of6);
		playpayout[14] = String.valueOf(SumWin2of6plusBonus);

		return playpayout;
	}

}
