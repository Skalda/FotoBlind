package com.example.ideChoser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Navigation extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        //  Toast.makeText(this, "Navigace", Toast.LENGTH_SHORT).show();
        //getActionBar().hide();
    }

    public void clickSound(View view) {

        StaticData.NAVIGATION = 2;
        nextScreen();
    }

    public void clickVibration(View view) {

        StaticData.NAVIGATION = 3;
        nextScreen();
    }

    public void clickThird(View view) {
        StaticData.NAVIGATION = 1;
        nextScreen();
    }

    public void nextScreen() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
}
