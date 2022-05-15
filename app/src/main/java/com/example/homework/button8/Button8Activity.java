package com.example.homework.button8;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.homework.R;

public class Button8Activity extends Activity {
    private LinearLayout linearLayout;
    private ListenerReceiver listenerReceiver;
    private TextView textView0,textView1,textView2,textView3;
    int x = 0;
    String maxMemory = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button8_activity);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        textView0 = (TextView) findViewById(R.id.textView0);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        maxMemory = getResources().getString(R.string.current_memory);
        textView0.setText(maxMemory);

        listenerReceiver = new ListenerReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("com.test.broadcast.MEMORY_CHANGE");
        intentFilter.setPriority(100);
        registerReceiver(listenerReceiver,intentFilter);
    }
    class ListenerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);

            float maxMemory1 = (float) (Runtime.getRuntime().maxMemory() * 1.0/ (1024 * 1024));
            textView1.setText(""+maxMemory1);
            float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0/ (1024 * 1024));
            textView2.setText(""+totalMemory);
            float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0/ (1024 * 1024));
            textView3.setText(""+freeMemory);

            TextView textView = new TextView(Button8Activity.this);
            textView.setText(x+"s系统剩余内存为：" + String.valueOf(memoryInfo.availMem / (1024 * 1024)) + "MB");
            linearLayout.addView(textView);
            if (x == 5){
                if (maxMemory.equals(String.valueOf(maxMemory1))){
                    textView0.setText("最大分配内存相同");
                }
            }
            try {
                Thread.sleep(1000);
                x++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            {
                if (x < 20){
                    intent.setAction("com.test.broadcast.MEMORY_CHANGE");
                    context.sendOrderedBroadcast(intent,null);
                }else{
                    abortBroadcast();
                }
            }
        }
    }

}
