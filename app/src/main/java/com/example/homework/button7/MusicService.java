package com.example.homework.button7;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MusicService extends Service {
    public final IBinder binder = new MyBinder();

    private String path;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        path = intent.getStringExtra("path");
        init();
        return binder;
    }

    public class MyBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }
    public static MediaPlayer mp = new MediaPlayer();

    public MusicService() {
    }
    public void init(){
        try {
            Log.i("aa",path);
            mp.setDataSource(path);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void play(){
        mp.start();
    }
    public void stop() throws IOException {
        if (mp != null){
            mp.stop();
            mp.prepare();
            mp.seekTo(0);
        }
    }
}
