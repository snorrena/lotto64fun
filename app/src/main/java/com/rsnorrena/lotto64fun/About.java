package com.rsnorrena.lotto64fun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends LicenseCheckActivity{
	Button AboutNext;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

//        Toast.makeText(this, "Checking Application License", Toast.LENGTH_SHORT).show();

//        Toast localToast = Toast.makeText(getApplicationContext(),
//                "Checking Application License",
//                Toast.LENGTH_SHORT);
//        localToast.setGravity(Gravity.CENTER, 0, 0);
//        localToast.show();
        // Check the license
//        checkLicense();

        AboutNext = (Button) findViewById(R.id.bANext);
        AboutNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    Class<?> ourClass = Class.forName("com.rsnorrena.lotto64fun.MainActivity");
                    Intent ourIntent = new Intent(About.this, ourClass);
                    startActivity(ourIntent);
                    finish();
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        });
    }

	
}

