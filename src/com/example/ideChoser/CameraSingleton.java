package com.example.ideChoser;

import android.hardware.Camera;

/**
 * Created by MV-pracovni on 4.5.2014.
 */
public class CameraSingleton {

    private static Camera instance;

    public static Camera GetInstance(){
        if(instance == null){
            instance = Camera.open();
        }

        return instance;
    }

    public static void ReleaseInstance(){
        if(instance != null){
            instance.release();
            instance = null;
        }
    }
}
