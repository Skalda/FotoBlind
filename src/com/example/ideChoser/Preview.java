package com.example.ideChoser;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by mejty on 11.5.14.
 */
public class Preview extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);
        ImageView nahled = (ImageView) findViewById(R.id.previewImg);
        //nahled.setBackgroundDrawable(BitmapDrawable.createFromPath(StaticData.IMAGE_FILE.getPath()));
        nahled.setImageBitmap(BitmapFactory.decodeFile(StaticData.IMAGE_FILE.getPath()));
    }
}