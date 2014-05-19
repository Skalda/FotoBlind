package com.example.ideChoser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Skalda on 20.4.2014.
 */
public class CameraActivity extends Activity {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final String TAG = "CameraActivity";
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }

            Log.d(TAG, "OK");

            Intent intent = new Intent(getBaseContext(), Final.class);
            startActivity(intent);
        }
    };
    private Camera mCamera;

    private TextToSpeech mTTS;
    private ToneGenerator toneG;
    private Vibrator myVibrator;

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = StaticData.IMAGE_DIRECTORY;

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        StaticData.IMAGE_FILE = mediaFile;

        /*if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }*/

        return mediaFile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.camera);

        boolean initialized = initialize(this);

        if (!initialized) {
            this.finish();
        }

        mTTS = new TextToSpeech(this, null);
        toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
        myVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Create our com.example.ideChoser.Preview view and set it as the content of our activity.
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(new CameraPreview(this));

        // Add a listener to the Capture button
        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        mCamera.takePicture(null, null, mPicture);
                        Log.d(TAG, "Vyfoceno");
                    }
                }
        );

        /*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                System.err.println(ex);
                ex.printStackTrace(System.err);
            }
        });*/

    }

    private boolean initialize(Context context) {

        boolean hasCamera = checkCameraHardware(context);

        if (!hasCamera) {
            Log.d(TAG, "Device does not have camera.");
            Toast.makeText(context, "Device does not have camera.", Toast.LENGTH_LONG).show();
            return false;
        }

        File imageDir = StaticData.IMAGE_DIRECTORY;

        if (!imageDir.exists() && !imageDir.mkdir()) {
            Log.d(TAG, "Can't create directory to save image.");
            Toast.makeText(context, "Can't create directory to save image.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onStart() {
        System.out.println("onStart");
        super.onStart();
        // Create an instance of Camera
        mCamera = CameraSingleton.GetInstance();
        this.setCameraDisplayOrientation(this, 1, mCamera);
        Camera.Parameters camParameters = mCamera.getParameters();
        camParameters.setFocusMode("continuous-picture");
        Camera.Size max = camParameters.getSupportedPictureSizes().get(0);
        int maxsize = 0;
        for (Camera.Size s : camParameters.getSupportedPictureSizes()){
            int tmpSize = s.height * s.width;
            if(tmpSize > maxsize){
                maxsize = tmpSize;
                max = s;
            }
        }
        camParameters.setPictureSize(max.width, max.height);
        mCamera.setParameters(camParameters);
        mCamera.setFaceDetectionListener(new MyFaceDetectionListener(mTTS,toneG,myVibrator));
    }

    @Override
    protected void onStop() {
        System.out.println("onStop");
        super.onStop();
        mTTS.shutdown();
        mCamera.stopFaceDetection();
        CameraSingleton.ReleaseInstance();
    }

    /**
     * Check if this device has a camera
     */
    private boolean checkCameraHardware(Context context) {
        // this device has a camera
// no camera on this device
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }



}