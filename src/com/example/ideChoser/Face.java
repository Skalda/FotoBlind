package com.example.ideChoser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.content.Context;

public class Face extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_size);
        Toast.makeText(this, "Druh fotky", Toast.LENGTH_SHORT).show();
        //getActionBar().hide();
    }

    public void clickFirst(View view) {

        Intent intent = new Intent(this, Face.class);
        startActivity(intent);
    }

    public void clickSecond(View view) {

        Intent intent = new Intent(this, Navigation.class);
        startActivity(intent);
    }

    public void clickThird(View view) {
        // setContentView(R.layout.final_landscape);
        //Toast.makeText(this, "Click on third button", Toast.LENGTH_SHORT).show();
    }
}
