package com.example.ideChoser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Final extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalay);
        ImageButton nahled = (ImageButton) findViewById(R.id.nahledBtn);
     //   Uri uri = Uri.fromFile(StaticData.IMAGE_FILE);
       // BitmapDrawable.createFromPath(StaticData.IMAGE_FILE.getPath());
        nahled.setBackground(BitmapDrawable.createFromPath(StaticData.IMAGE_FILE.getPath()));
        //getActionBar().hide();
    }

    public void clickPreview(View view) {

        Intent intent = new Intent(this, Preview.class);
        startActivity(intent);
    }

    public void clickFacebook(View view) {

        //add code here
    }

    public void clickDelete(View view) {

        //add code here
    }

    public void clickShare(View view) {

        //add code here
    }

    public void clickTwitter(View view) {

        //add code here
    }

    public void clickInstagram(View view) {

        //add code here
    }
}
