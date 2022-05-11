package com.example.homework.button6;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Timer;
import java.util.TimerTask;

public class Button6Activity extends AppCompatActivity {
    private EditText editText;
    private Button download,pause ;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private TextView times;
    private TextView result;
    boolean state = true;
    private Handler handler;
    private static MediaPlayer mediaPlayer = null;
    private int iTimes = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button6_activity);
        editText = findViewById(R.id.url);
        download = findViewById(R.id.download);
        pause = findViewById(R.id.pause);
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        times = findViewById(R.id.times);
        result = findViewById(R.id.resultm);

        String url = editText.getText().toString();
        editText.setText("http://freetyst.nf.migu.cn/public/product5th/product34/2019/06/2523/2017%E5%B9%B412%E6%9C%8812%E6%97%A516%E7%82%B943%E5%88%86%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E5%A4%A7%E9%BE%99%E6%96%87%E5%8C%96986%E9%A6%96/%E6%A0%87%E6%B8%85%E9%AB%98%E6%B8%85/MP3_128_16_Stero/63880010743.mp3?channelid=02&msisdn=a6071dbb-2de0-4c39-a568-a68b8e795bcc&Tim=1652258246975&Key=883cadacbe500fa1");

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.length() != 0){
                    if (state){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                download();
                            }
                        }).start();
                        state = false;
                    }else {
                        Toast.makeText(Button6Activity.this, "正在下载", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Button6Activity.this, "请输入下载地址", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x101){
                    progressBar1.setProgress(msg.arg1);
                    result.setText("已下载" + msg.arg1 + "%");
                    if (msg.arg1 == 100){
                        result.setText("下载完成！");
                    }
                }
                if (msg.what == 0x102){
                    progressBar1.setProgress(msg.arg1);
                }
                super.handleMessage(msg);
            }
        };

    }


    private void download() {
        try {
            URL url = new URL(editText.getText().toString());
            URLConnection urlConnection = url.openConnection();
            int length = urlConnection.getContentLength();
            ReadableByteChannel c = Channels.newChannel(url.openStream());
            File file = new File("music.mp3");
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel channel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int len;
            int sum = 0;
            while ((len = c.read(byteBuffer)) != -1){
                sum += len;
                int progress = (sum*100)/length;
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    channel.write(byteBuffer);
                }
                byteBuffer.clear();
                Message m = handler.obtainMessage();
                m.what = 0x101;
                m.arg1 = progress;
                handler.sendMessage(m);
            }
            display();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void display() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this,Uri.parse("music.mp3"));
        mediaPlayer.start();

        progressBar2.setMax(mediaPlayer.getDuration());
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                progressBar2.setProgress(mediaPlayer.getCurrentPosition());
            }
        };
        timer.schedule(timerTask,0,10);

        iTimes++;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                times.setText(""+iTimes);
                display();
            }
        });
    }

}
