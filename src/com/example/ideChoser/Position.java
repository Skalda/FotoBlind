package com.example.ideChoser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.content.Context;

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
        final Context context = this;
        Intent intent = new Intent(context, Face.class);
        startActivity(intent);
    }

    public void clickSecond(View view) {

        Toast.makeText(this, "Navigace", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.navigation);
    }

    public void clickThird(View view) {
        // setContentView(R.layout.final_landscape);
        //Toast.makeText(this, "Click on third button", Toast.LENGTH_SHORT).show();
    }
}
