package com.rsnorrena.lotto64fun;

/**
 * Created by Admin on 12/23/2014.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;



import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.Policy;
import com.google.android.vending.licensing.ServerManagedPolicy;

public abstract class LicenseCheckActivity extends Activity {

    static boolean licensed = true;
    static boolean didCheck = false;
    static boolean checkingLicense = false;
    static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7WbRKt+aMxqcUkA6tXrsWo2Qt3QPqFrdz+3awNJcV3cvRuHuF3U4vrU0ujM3SYDrrEXrzGLvS2bBRrN4LMRQvM+OK0pvFSwto8ZWrU1XwhsJZa8/kj3bZ8ZKP87r1lrInlCMeziQZhXP4ZkbaKn8Rcn1qDIz+ElFHl9Fr7y6XMFiJ2CWbvBqeV0muU+b2ptYMcdP745PPswR6RFpaojAwmGCYWmwfCyLzSadDjQmx50v5UbJa3yQ8EWRetsOeyY3LZO+NRWlppP7U98gJ6aOJhdR+dil2gr6t+FWJty4vPhiKdBxSHmfslZl5QS1RXnu74Vhv9mrPSiRDq0x38tsuQIDAQAB";

    com.google.android.vending.licensing.LicenseCheckerCallback mLicenseCheckerCallback;
    com.google.android.vending.licensing.LicenseChecker mChecker;

    Handler mHandler;

    SharedPreferences prefs;

    // REPLACE WITH YOUR OWN SALT , THIS IS FROM EXAMPLE
    private static final byte[] SALT = new byte[] { 97, 98, 56, 70, 46, 00, 37, 81, 59, 06, 57, 45, 53, 01, 62, 02, 13, 77, 25, 74 };

    private void displayResult(final String result) {
        mHandler.post(new Runnable() {
            public void run() {

                setProgressBarIndeterminateVisibility(false);

            }
        });
    }

    protected void doCheck() {

        didCheck = false;
        checkingLicense = true;
        setProgressBarIndeterminateVisibility(true);

        mChecker.checkAccess(mLicenseCheckerCallback);
    }

    protected void checkLicense() {

        Log.i("LICENSE", "checkLicense");
        mHandler = new Handler();

        // Try to use more data here. ANDROID_ID is a single point of attack.
        String deviceId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        // Library calls this when it's done.
        mLicenseCheckerCallback = new MyLicenseCheckerCallback();
        // Construct the LicenseChecker with a policy.
        mChecker = new com.google.android.vending.licensing.LicenseChecker(this, (com.google.android.vending.licensing.Policy) new com.google.android.vending.licensing.ServerManagedPolicy(
                this, new com.google.android.vending.licensing.AESObfuscator(SALT, getPackageName(), deviceId)),
                BASE64_PUBLIC_KEY);

        // mChecker = new LicenseChecker(
        // this, new StrictPolicy(),
        // BASE64_PUBLIC_KEY);

        doCheck();
    }

    protected class MyLicenseCheckerCallback implements com.google.android.vending.licensing.LicenseCheckerCallback {

        @Override
        public void allow(int reason) {
            // TODO Auto-generated method stub
            Log.i("LICENSE", "allow");
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            // Should allow user access.
            displayResult(getString(R.string.allow));
            licensed = true;
            checkingLicense = false;
            didCheck = true;

        }

        @SuppressWarnings("deprecation")
        @Override
        public void dontAllow(int reason) {
            // TODO Auto-generated method stub
            Log.i("LICENSE", "dontAllow: " + reason);
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            displayResult(getString(R.string.dont_allow));
            licensed = false;
            // Should not allow access. In most cases, the app should assume
            // the user has access unless it encounters this. If it does,
            // the app should inform the user of their unlicensed ways
            // and then either shut down the app or limit the user to a
            // restricted set of features.
            // In this example, we show a dialog that takes the user to Market.
            checkingLicense = false;
            didCheck = true;

            showDialog(0);

        }

        @SuppressWarnings("deprecation")
        @Override
        public void applicationError(int errorCode) {
            // TODO Auto-generated method stub
            Log.i("LICENSE", "error: " + errorCode);
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            licensed = false;
            // This is a polite way of saying the developer made a mistake
            // while setting up or calling the license checker library.
            // Please examine the error code and fix the error.
            String result = String.format(
                    getString(R.string.application_error), errorCode);
            checkingLicense = false;
            didCheck = true;

            displayResult(result);
            showDialog(0);

        }
    }

    protected Dialog onCreateDialog(int id) {
        // We have only one dialog.
        return new AlertDialog.Builder(this)
                .setTitle(R.string.unlicensed_dialog_title)
                .setMessage(R.string.unlicensed_dialog_body)
                .setPositiveButton(R.string.buy_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent marketIntent = new Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("http://market.android.com/details?id="
                                                + getPackageName()));
                                startActivity(marketIntent);
                                finish();
                            }
                        })
                .setNegativeButton(R.string.quit_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        })

                .setCancelable(false)
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialogInterface,
                                         int i, KeyEvent keyEvent) {
                        Log.i("License", "Key Listener");
                        finish();
                        return true;
                    }
                }).create();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChecker != null) {
            Log.i("License", "destroy checker");
            mChecker.onDestroy();
        }
    }

}