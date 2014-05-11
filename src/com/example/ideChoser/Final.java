package com.example.ideChoser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        share("facebook.katana", StaticData.IMAGE_FILE.getPath().toString());
        //add code here
    }

    public void clickDelete(View view) {

        //add code here
    }

    public void clickShare(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("image/*");

        // For a file in shared storage.  For data in private storage, use a ContentProvider.
        Uri uri = Uri.fromFile(StaticData.IMAGE_FILE);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Sdílet na"));
        //add code here
    }

    public void clickTwitter(View view) {
        share("com.twitter.android", StaticData.IMAGE_FILE.getPath().toString());
        //add code here
    }

    public void clickInstagram(View view) {

        share("instagram", StaticData.IMAGE_FILE.getPath().toString());
        //add code here
    }

    /**
     * To share photo with text on facebook , twitter and email etc.@param nameApp
     *
     * @param nameApp
     * @param imagePath
     */

    void share(String nameApp, String imagePath) {
        try {
            System.out.println("Share started");
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("image/jpg");
            List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(share, 0);
            System.out.println(!resInfo.isEmpty());
            if (!resInfo.isEmpty()) {
                for (ResolveInfo info : resInfo) {
                    Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
                    targetedShare.setType("image/jpg"); // put here your mime type
//                    System.out.println(info.activityInfo.packageName.toLowerCase());
//                    System.out.println(info.activityInfo.name.toLowerCase());
                    if (info.activityInfo.packageName.toLowerCase().contains(nameApp) || info.activityInfo.name.toLowerCase().contains(nameApp)) {
                        // targetedShare.putExtra(Intent.EXTRA_SUBJECT, "Sample Photo");
                        // targetedShare.putExtra(Intent.EXTRA_TEXT,"Created by FotoBlind");
                        targetedShare.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imagePath)));
                        targetedShare.setPackage(info.activityInfo.packageName);
                        targetedShareIntents.add(targetedShare);
//                        System.out.println(info.activityInfo.packageName.toLowerCase());
//                        System.out.println(info.activityInfo.name.toLowerCase());
                    }
                }
                Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Select app to share");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
                startActivity(chooserIntent);
            }
        } catch (Exception e) {
            Log.v("VM", "Exception while sending image on" + nameApp + " " + e.getMessage());
        }
    }
}
