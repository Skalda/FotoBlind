package com.example.ideChoser;

import android.os.Environment;

import java.io.File;

/**
 * Created by MV-pracovni on 4.5.2014.
 */
public class StaticData {
    public static final String APP_NAME = "FotoBlind";
    public static final File IMAGE_DIRECTORY = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), APP_NAME);
    public static File IMAGE_FILE = null;
    public static int FACE_POSITION = 1;
    public static int FACE_SIZE = 1;
    public static int NAVIGATION = 1;
}