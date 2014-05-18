package com.example.ideChoser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Position extends Activity {
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
        StaticData.FACE_POSITION = 1;
        nextScreen();
    }

    public void nextScreen() {
        final Context context = this;
        Intent intent = new Intent(context, Face.class);
        startActivity(intent);
    }

    public void clickLeft(View view) {

        StaticData.FACE_POSITION = 2;
        nextScreen();
    }

    public void clickCenter(View view) {

        StaticData.FACE_POSITION = 3;
        nextScreen();
    }


}
