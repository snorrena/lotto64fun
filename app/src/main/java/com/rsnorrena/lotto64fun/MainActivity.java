package com.rsnorrena.lotto64fun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;


public class MainActivity extends Activity implements OnClickListener {

    public static final String MAIN_ACTIVITY = "MainActivity";
    Button bMainActivityPlay, bQuickPick, breSetTextFields;
    ToggleButton tbAppAudio;
	EditText etMAN1, etMAN2, etMAN3, etMAN4, etMAN5, etMAN6;

	private int soundID1, soundID2;
	Double grandprizewinner, secondprizewinner;
	boolean loaded = false;
	boolean numberscheck = true;
	private SoundPool mSoundPool;
    boolean Audio;
    boolean TinyDBAudio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);





		initializeSoundPool();
		initialize();



        toggleAudio();





		// check for changes in the edit text fields

		etMAN1.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etMAN1.getText().toString().length() == 2) // size as per
																// your
																// requirement
				{
					etMAN2.requestFocus();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

		});

		etMAN2.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etMAN2.getText().toString().length() == 2) // size as per
																// your
																// requirement
				{
					etMAN3.requestFocus();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

		});

		etMAN3.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etMAN3.getText().toString().length() == 2) // size as per
																// your
																// requirement
				{
					etMAN4.requestFocus();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

		});

		etMAN4.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etMAN4.getText().toString().length() == 2) // size as per
																// your
																// requirement
				{
					etMAN5.requestFocus();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

		});

		etMAN5.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (etMAN5.getText().toString().length() == 2) // size as per
																// your
																// requirement
				{
					etMAN6.requestFocus();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

		});
	}

    private void toggleAudio() {

        TinyDB localTinyDB2;

        localTinyDB2 = new TinyDB(this);
        TinyDBAudio = localTinyDB2.getBoolean("TinyDBAudio");

        if (TinyDBAudio){
        tbAppAudio.setChecked(true);
        Audio = true;
        localTinyDB2.putBoolean("TinyDBAudio", true);
        }else if (!TinyDBAudio){
        tbAppAudio.setChecked(false);
        Audio = false;
        localTinyDB2.putBoolean("TinyDBAudio", false);
        }else{
        tbAppAudio.setChecked(true);
        Audio = true;
        localTinyDB2.putBoolean("TinyDBAudio", true);
        }
    }

    private void PlaySoundPoolFile() {
		// Getting the user sound settings
		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		float actualVolume = (float) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = (float) audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;
		if (loaded && Audio ) {
			mSoundPool.play(this.soundID1, volume, volume, 1, 0, 1f);
			if ((this.grandprizewinner > 0.0D)
					|| (this.secondprizewinner > 0.0D)) {
				this.mSoundPool.play(this.soundID2, volume, volume, 1, 0, 1f);
			}

		}
	}

	private void initialize() {
		bMainActivityPlay = (Button) findViewById(R.id.bMAPlay);
		bMainActivityPlay.setOnClickListener(this);

		breSetTextFields = (Button) findViewById(R.id.bMAReset);
		breSetTextFields.setOnClickListener(this);

		bQuickPick = (Button) findViewById(R.id.bMARandom);
        bQuickPick.setOnClickListener(this);

        tbAppAudio = (ToggleButton) findViewById(R.id.tbMAMuteAudio);
        tbAppAudio.setOnClickListener(this);

		etMAN1 = (EditText) findViewById(R.id.etNo1);
		etMAN2 = (EditText) findViewById(R.id.etNo2);
		etMAN3 = (EditText) findViewById(R.id.etNo3);
		etMAN4 = (EditText) findViewById(R.id.etNo4);
		etMAN5 = (EditText) findViewById(R.id.etNo5);
		etMAN6 = (EditText) findViewById(R.id.etNo6);
	}

	private void initializeSoundPool() {
		// Set the hardware buttons to control the music
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Load the sound
		mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		mSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});
		soundID1 = mSoundPool.load(this, R.raw.slotmachine, 1);
		soundID2 = mSoundPool.load(this, R.raw.yahoo, 1);

	}

	public void onBackPressed() {
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bMAPlay:




			String num1 = etMAN1.getText().toString();
			String num2 = etMAN2.getText().toString();
			String num3 = etMAN3.getText().toString();
			String num4 = etMAN4.getText().toString();
			String num5 = etMAN5.getText().toString();
			String num6 = etMAN6.getText().toString();

			String[] EnteredNumbersArray = { num1, num2, num3, num4, num5, num6 };

			// call to method object to check validity of entered numbers and
			// return boolean true if all good
			CheckNumbers localCheckNumbers = new CheckNumbers();
			this.numberscheck = localCheckNumbers.GetBoolean(
					EnteredNumbersArray).booleanValue();

			if (this.numberscheck) {

				// send the entered numbers array to strip array numbers of any
				// leading zeros
				String[] ValidatedEnteredNumbersArray = localCheckNumbers
						.GetNewEnteredNumbersArray(EnteredNumbersArray);

				// create object of the database
				TestAdapter localTestAdapter = new TestAdapter(this);

				// code to encrypt a new database to sqlcipher on first run.
				// comment out/remove this code/method in all activities afterwards
				// remember to update the database name in the helper class on install of a new database

				// call to method to encrypt the database to sqlcipher format with a passphrase
//				try {
//					localTestAdapter.encrypt();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}


				localTestAdapter.createDatabase();
				localTestAdapter.open();

				// send the validated ticket number array for a win check in the
				// database object
				String[] playpayout = localTestAdapter
						.checknumbers(ValidatedEnteredNumbersArray);
				localTestAdapter.close();

				String DisplayPlayedNumber = ValidatedEnteredNumbersArray[0]
						+ "-" + ValidatedEnteredNumbersArray[1] + "-"
						+ ValidatedEnteredNumbersArray[2] + "-"
						+ ValidatedEnteredNumbersArray[3] + "-"
						+ ValidatedEnteredNumbersArray[4] + "-"
						+ ValidatedEnteredNumbersArray[5];
				NumberFormat localNumberFormat = NumberFormat
						.getCurrencyInstance(Locale.CANADA);

				// message to be displayed based on results
				String PlayResultMessage;

				String SumOfPlayPayout = playpayout[0];
				Double SumOfTicketCost = Double.valueOf(0.0D);
				Double SumOfWinsMinusTicketCost = 0.0D;

				Double TinyDBDailyPayout = 0.0D;
				Double TinyDBDailyTicketCost = 0.0D;
				int TinyDBDailyGamesPlayed = 0;

				Double TinyDBLifeToDatePayout = 0.0D;
				Double TinyDBLifeToDateTicketCost = 0.0D;
				int TinyDBLifeToDateGamesPlayed = 0;

				String DisplayFormatCurrentPayout = null;

				int DatabaseGamesPlayed = 0;

				TinyDB localTinyDB1;

				String CurrentDate, SavedDate;

				if (SumOfPlayPayout != null) {

					DisplayFormatCurrentPayout = localNumberFormat
							.format(new BigDecimal(playpayout[0]));
					DatabaseGamesPlayed = Integer.valueOf(playpayout[7])
							.intValue();

					SumOfTicketCost = Double.valueOf(playpayout[8]);
					SumOfWinsMinusTicketCost = Double.valueOf(Double.valueOf(
							playpayout[0]).doubleValue()
							- SumOfTicketCost.doubleValue());

					Calendar c = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
					CurrentDate = df.format(c.getTime());

					localTinyDB1 = new TinyDB(this);
					SavedDate = localTinyDB1.getString("SavedDate");

					// reset daily variables if the date has changed
					if (SavedDate.compareTo(CurrentDate) != 0) {
						localTinyDB1.putInt("TinyDBDailyGamesPlayed", 0);
						localTinyDB1.putDouble("TinyDBDailyTicketCost", 0.0D);
						localTinyDB1.putDouble("TinyDBDailyPayout", 0.0D);

						localTinyDB1.putString("SavedDate", CurrentDate);
					}

					// update daily variables in app preferences
					TinyDBDailyGamesPlayed = localTinyDB1
							.getInt("TinyDBDailyGamesPlayed");

					if (TinyDBDailyGamesPlayed == 0) {
						TinyDBDailyGamesPlayed = DatabaseGamesPlayed;
						localTinyDB1.putInt("TinyDBDailyGamesPlayed",
								DatabaseGamesPlayed);
					} else {
						TinyDBDailyGamesPlayed = TinyDBDailyGamesPlayed
								+ DatabaseGamesPlayed;
						localTinyDB1.putInt("TinyDBDailyGamesPlayed",
								TinyDBDailyGamesPlayed);
					}

					TinyDBDailyTicketCost = Double.valueOf(localTinyDB1
							.getDouble("TinyDBDailyTicketCost"));

					if (TinyDBDailyTicketCost == 0.0D) {

						TinyDBDailyTicketCost = SumOfTicketCost;
						localTinyDB1.putDouble("TinyDBDailyTicketCost",
								TinyDBDailyTicketCost.doubleValue());

					} else {
						TinyDBDailyTicketCost = Double
								.valueOf(TinyDBDailyTicketCost.doubleValue()
										+ SumOfTicketCost.doubleValue());
						localTinyDB1.putDouble("TinyDBDailyTicketCost",
								TinyDBDailyTicketCost.doubleValue());
					}

					TinyDBDailyPayout = Double.valueOf(localTinyDB1
							.getDouble("TinyDBDailyPayout"));

					if (TinyDBDailyPayout == 0.0D) {

						TinyDBDailyPayout = SumOfWinsMinusTicketCost;
						localTinyDB1.putDouble("TinyDBDailyPayout",
								SumOfWinsMinusTicketCost.doubleValue());

					} else {
						TinyDBDailyPayout = TinyDBDailyPayout
								+ SumOfWinsMinusTicketCost;
						localTinyDB1.putDouble("TinyDBDailyPayout",
								TinyDBDailyPayout.doubleValue());
					}

					// update life to date variables in app preferences

					TinyDBLifeToDateGamesPlayed = localTinyDB1
							.getInt("TinyDBLifeToDateGamesPlayed");

					if (String.valueOf(TinyDBLifeToDateGamesPlayed) == null) {
						TinyDBLifeToDateGamesPlayed = DatabaseGamesPlayed;
						localTinyDB1.putInt("TinyDBLifeToDateGamesPlayed",
								DatabaseGamesPlayed);
					} else {
						TinyDBLifeToDateGamesPlayed = TinyDBLifeToDateGamesPlayed
								+ DatabaseGamesPlayed;
						localTinyDB1.putInt("TinyDBLifeToDateGamesPlayed",
								TinyDBLifeToDateGamesPlayed);
					}

					TinyDBLifeToDateTicketCost = Double.valueOf(localTinyDB1
							.getDouble("TinyDBLifeToDateTicketCost"));

					if (TinyDBLifeToDateTicketCost == null) {

						TinyDBLifeToDateTicketCost = SumOfTicketCost;
						localTinyDB1.putDouble("TinyDBLifeToDateTicketCost",
								TinyDBLifeToDateTicketCost.doubleValue());

					} else {
						TinyDBLifeToDateTicketCost = Double
								.valueOf(TinyDBLifeToDateTicketCost
										.doubleValue()
										+ SumOfTicketCost.doubleValue());
						localTinyDB1.putDouble("TinyDBLifeToDateTicketCost",
								TinyDBLifeToDateTicketCost.doubleValue());
					}

					TinyDBLifeToDatePayout = Double.valueOf(localTinyDB1
							.getDouble("TinyDBLifeToDatePayout"));

					if (TinyDBLifeToDatePayout == null) {

						TinyDBLifeToDatePayout = SumOfWinsMinusTicketCost;
						localTinyDB1.putDouble("TinyDBLifeToDatePayout",
								SumOfWinsMinusTicketCost.doubleValue());

					} else {
						TinyDBLifeToDatePayout = TinyDBLifeToDatePayout
								+ SumOfWinsMinusTicketCost;
						localTinyDB1.putDouble("TinyDBLifeToDatePayout",
								TinyDBLifeToDatePayout.doubleValue());
					}

				}

				if (playpayout[16] != null) {
					PlayResultMessage = playpayout[16];

				} else if (SumOfWinsMinusTicketCost.doubleValue() > 0.0D) {
					PlayResultMessage = "Wow! You have beaten the odds. Too bad we are only playing for Lotto64 Fun.";
				} else {
					PlayResultMessage = "Thankfully you are only playing Lotto64fun. You saved a small fortune in the cost of lottery tickets.";
				}

				Intent localIntent = new Intent(this, DidIWin.class);
				Bundle localBundle = new Bundle();

				localBundle.putString("win", PlayResultMessage);
				localBundle.putString("playednumber", DisplayPlayedNumber);
				localBundle.putString("payoutbeforeticketcost",
						DisplayFormatCurrentPayout);
				localBundle.putString("6of6matched", playpayout[1]);

				localBundle.putString("5of6plusbonusmatched", playpayout[2]);
				localBundle.putString("5of6matched", playpayout[3]);
				localBundle.putString("4of6matched", playpayout[4]);
				localBundle.putString("3of6matched", playpayout[5]);
				localBundle.putString("2of6plusbonusmatched", playpayout[6]);
				localBundle.putString("6of6matchedpayout", playpayout[9]);
				localBundle.putString("5of6plusbonusmatchedpayout",
						playpayout[10]);
				localBundle.putString("5of6matchedpayout", playpayout[11]);
				localBundle.putString("4of6matchedpayout", playpayout[12]);
				localBundle.putString("3of6matchedpayout", playpayout[13]);
				localBundle.putString("2of6plusbonusmatchedpayout",
						playpayout[14]);
				localBundle.putInt("MAcurrentnumberoftickets",
						DatabaseGamesPlayed);
				localBundle.putDouble("MAcurrentticketcost",
						SumOfTicketCost.doubleValue());
				localBundle.putDouble("MAcurrentdisplaypayoutATC",
						SumOfWinsMinusTicketCost.doubleValue());
				localBundle.putInt("MAdailynumberoftickets",
						TinyDBDailyGamesPlayed);
				localBundle.putDouble("MAdailyticketscost",
						TinyDBDailyTicketCost.doubleValue());
				localBundle.putDouble("MAdailydisplaypayoutATC",
						TinyDBDailyPayout.doubleValue());
				localBundle.putInt("MAlifetodatenumberoftickets",
						TinyDBLifeToDateGamesPlayed);
				localBundle.putDouble("MAlifetodateticketcost",
						TinyDBLifeToDateTicketCost.doubleValue());
				localBundle.putDouble("MAlifetodatedisplaypayoutATC",
						TinyDBLifeToDatePayout.doubleValue());
				localIntent.putExtras(localBundle);

				grandprizewinner = Double.valueOf(playpayout[9]);
				secondprizewinner = Double.valueOf(playpayout[10]);

				startActivity(localIntent);
				PlaySoundPoolFile();
//				finish();

			} else {

				Toast localToast = Toast.makeText(getApplicationContext(),
						"Enter six unique numbers between 1 & 49.",
						Toast.LENGTH_SHORT);
				localToast.setGravity(Gravity.CENTER, 0, 0);
				localToast.show();
			}

			break;

		// case to clear the edit text fields
		case R.id.bMAReset:

			etMAN1.setText("");
			etMAN2.setText("");
			etMAN3.setText("");
			etMAN4.setText("");
			etMAN5.setText("");
			etMAN6.setText("");

			break;

		// case to generate a ticket with random numbers
		case R.id.bMARandom:

			int[] FourtyNineNumbers = new int[49];
			int j = 0;
			for (int i = 0; i < FourtyNineNumbers.length; i++) {
				j++;
				FourtyNineNumbers[i] = (j);
			}

			int index,
			temp;
			Random random = new Random();
			for (int i = FourtyNineNumbers.length - 1; i > 0; i--) {//loop through the array length of 0 - 48.
				index = random.nextInt(i + 1);
				temp = FourtyNineNumbers[index];// temp assignment of the vale at the random index
				FourtyNineNumbers[index] = FourtyNineNumbers[i];//assignment of the current loop array value to the random index
				FourtyNineNumbers[i] = temp;
			}

			int[] newArray = Arrays.copyOfRange(FourtyNineNumbers, 0, 6);//take the first 6 values from the newly shufled array

			String[] Random = new String[6];

			for (int i = 0; i < newArray.length; i++) {
				Random[i] = String.valueOf(newArray[i]);
			}

			etMAN1.setText(Random[0]);
			etMAN2.setText(Random[1]);
			etMAN3.setText(Random[2]);
			etMAN4.setText(Random[3]);
			etMAN5.setText(Random[4]);
			etMAN6.setText(Random[5]);

			break;

            case R.id.tbMAMuteAudio:

//                TinyDB localTinyDB2;
//
//                localTinyDB2 = new TinyDB(this);
//
//                TinyDBAudio = localTinyDB2.getBoolean("TinyDBAudio");
//
//                if (TinyDBAudio){
//                    tbAppAudio.setChecked(false);
//                    localTinyDB2.putBoolean("TinyDBAudio", false);
//                }else if (!TinyDBAudio){
//                    tbAppAudio.setChecked(true);
//                    localTinyDB2.putBoolean("TinyDBAudio", true);
//                }else{
//                    tbAppAudio.setChecked(true);
//                    localTinyDB2.putBoolean("TinyDBAudio", true);
//                }
                TinyDB localTinyDB2;

                localTinyDB2 = new TinyDB(this);
                String AudioSound = "";

                if (tbAppAudio.isChecked()){
                    localTinyDB2.putBoolean("TinyDBAudio", true);
                    Audio = true;
                    AudioSound = "on.";
                }else{
                    localTinyDB2.putBoolean("TinyDBAudio", false);
                    Audio = false;
                    AudioSound = "off.";
                }


//                String AudioSound = "";
//
//                if (Audio) {
//                    Audio = false;
//                    AudioSound = "off.";
//                }
//                else {
//                    Audio = true;
//                    AudioSound = "on.";

//                };


                Toast localToast = Toast.makeText(getApplicationContext(),
                        "Application audio is "+ AudioSound,
                        Toast.LENGTH_SHORT);
//                localToast.setGravity(Gravity.CENTER, 0, 0);
                localToast.setGravity(Gravity.CENTER, 0, 0);
                localToast.show();




                break;

		}

	}

}
