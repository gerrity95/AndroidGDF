package com.example.mark.androidgdf;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gerrity95 on 29/10/16.
 *
 * This is to a method to call any methods related to any assets
 */

public class Assets {

    public static SoundPool soundPool;
    public static Bitmap welcome;



    public static void load() //this is to load the welcome screen
    {
        welcome = loadBitmap("welcome.png", false);
    }
    /* The following is to with image related assets
        It is done to load images from the filesystem into RAM memory

        loadBitmap accepts booleans so we can specify if we want to load a transparent image or not
    */
    private static Bitmap loadBitmap(String filename, boolean transparency)
    {
        InputStream inputStream = null;
        try {
            inputStream = GameMainActivity.assets.open(filename);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        if(transparency)
        {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888; //ARGB_8888 means transparent images and more memory consumption
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565; //RGB_565 means no transparency and thus less memory consumption
        }

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, new BitmapFactory.Options());

        return bitmap;
    }

    /*
    This below is used to load short sounds into RAM
     */

    private static int loadSound(String filename)
    {
        int soundID = 0;
        if (soundPool == null)
        {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                createNewSoundPool();
            }else{
                createOldSoundPool();
            }



        }
        try {
            soundID = soundPool.load(GameMainActivity.assets.openFd(filename),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soundID;
    }


    public static void playSound(int soundID)
    {
        soundPool.play(soundID, 1, 1, 1, 0, 1);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected static void createNewSoundPool(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    protected static void createOldSoundPool(){
        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
    }

}
