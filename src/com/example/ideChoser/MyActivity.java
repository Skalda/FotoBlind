package com.example.ideChoser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);
        //getActionBar().hide();
    }

    public void clickFirst(View view) {
        Toast.makeText(this, "Click on first button", Toast.LENGTH_LONG).show();
    }

    public void clickSecond(View view) {
        Toast.makeText(this, "Click on second button", Toast.LENGTH_LONG).show();
    }

    public void clickThird(View view) {
        Toast.makeText(this, "Click on third button", Toast.LENGTH_LONG).show();
    }
}
