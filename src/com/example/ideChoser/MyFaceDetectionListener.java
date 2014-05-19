package com.example.ideChoser;

import android.content.Context;
import android.hardware.Camera;
import android.media.ToneGenerator;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;

/**
 * Created by Skalda on 19.5.2014.
 */
class MyFaceDetectionListener implements Camera.FaceDetectionListener {

    private TextToSpeech mTTS;
    private ToneGenerator toneG;
    private Vibrator myVibrator;

    public MyFaceDetectionListener(TextToSpeech mTTS, ToneGenerator toneG, Vibrator myVibrator) {
        super();
        this.mTTS = mTTS;
        this.toneG = toneG;
        this.myVibrator = myVibrator;
    }

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces.length > 0){
            switch (StaticData.NAVIGATION){
                case 1:
                    mTTS.speak("Face detected", TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 2:
                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                    break;
                case 3:
                    // Vibrate for 500 milliseconds
                    myVibrator.vibrate(500);
                    break;
            }
            Log.d("FaceDetection", "face detected: " + faces.length +
                    " Face 1 Location X: " + faces[0].rect.centerX() +
                    "Y: " + faces[0].rect.centerY());
        }
    }
}
