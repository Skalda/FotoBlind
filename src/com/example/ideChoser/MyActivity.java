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
        setContentView(R.layout.position);
        Toast.makeText(this, "Zarovnání obličeje", Toast.LENGTH_SHORT).show();
        //getActionBar().hide();
    }

    public void clickFirst(View view) {

        Toast.makeText(this, "Druh fotografie", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.face_size);
    }

    public void clickSecond(View view) {

        Toast.makeText(this, "Navigace", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.navigation);
    }

    public void clickThird(View view) {

        //Toast.makeText(this, "Click on third button", Toast.LENGTH_SHORT).show();
    }
}
