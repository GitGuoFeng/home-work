package com.example.homework.button1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.homework.R;


public class Main3Activity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Handler mHandler;
    private int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        final ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    progressBar.setProgress(mProgressStatus);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Main3Activity.this, "加载完成", Toast.LENGTH_SHORT).show();
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProgressStatus = doWork();
                    Message m = new Message();
                    if (mProgressStatus < 100) {
                        m.what = 0x111;
                        mHandler.sendMessage(m);
                    } else {
                        m.what = 0x110;
                        mHandler.sendMessage(m);
                        break;
                    }
                }
            }

            private int doWork() {
                mProgressStatus += Math.random() * 10;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mProgressStatus;
            }
        }).start();
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        imageView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(Main3Activity.this).create();
                alertDialog.setIcon(R.drawable.ic_launcher_background);
                alertDialog.setTitle("删除？");
                alertDialog.setMessage("真的要删除了嘛");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "手滑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        linearLayout.removeView(imageView2);
                    }
                });
                alertDialog.show();
                return true;
            }
        });
        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(Main3Activity.this).create();
                alertDialog.setIcon(R.drawable.ic_launcher_background);
                alertDialog.setTitle("删除？");
                alertDialog.setMessage("真的要删除了嘛");
                //添加取消按钮
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "手滑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        linearLayout.removeView(imageView1);
                    }
                });
                alertDialog.show();
                return true;
            }
        });
    }
}