package com.example.ideChoser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLOutput;

public class Face extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_size);
        //  Toast.makeText(this, "Druh fotky", Toast.LENGTH_SHORT).show();
        //getActionBar().hide();
    }


    public void clickSecond(View view) {
        StaticData.FACE_SIZE = 1;
        nextScreen();
    }

    public void clickPolocelek(View view) {
        StaticData.FACE_SIZE = 2;
        nextScreen();
    }

    public void nextScreen() {
        Intent intent = new Intent(this, Navigation.class);
        startActivity(intent);
    }

    public void clickFreestyle(View view) {
        StaticData.FACE_SIZE = 3;
        nextScreen();
    }
}
