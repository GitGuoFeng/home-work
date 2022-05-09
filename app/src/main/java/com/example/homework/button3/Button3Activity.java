package com.example.homework.button3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.R;


public class Button3Activity extends AppCompatActivity {
    private NetworkChange networkChange;
    private final static String ACTION_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button3_activity);

        TextView textView1= (TextView) findViewById(R.id.textView31);
        TextView textView2= (TextView) findViewById(R.id.textView32);
        TextView textView3= (TextView) findViewById(R.id.textView33);

        Intent intent = getIntent();
        int a = intent.getIntExtra("int",0);
        byte b = intent.getByteExtra("byte", (byte)0);
        SerializableObject object = (SerializableObject) intent.getSerializableExtra("object");

        textView1.setText("int数据为：" + a);
        textView2.setText("byte数据为：" + b);
        textView3.setText("Serializable数据为：id:"+object.getId()+"name:"+object.getName());

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_CHANGE);
        networkChange = new NetworkChange();
        registerReceiver(networkChange,intentFilter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChange);
    }

    class NetworkChange extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                Toast.makeText(Button3Activity.this, "已连网！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Button3Activity.this, "已断网！", Toast.LENGTH_LONG).show();
            }
        }
    }
}