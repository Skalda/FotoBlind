package com.example.ideChoser;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
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
        //priraveno dopln svoji activitu

        // Intent intent = new Intent(this, TVOJE_ACTIVITA.class);
        //startActivity(intent);
    }




}
