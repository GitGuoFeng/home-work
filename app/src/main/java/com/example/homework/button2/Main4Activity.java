package com.example.homework.button2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.homework.R;


public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragment fragment1;
    private Fragment fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText("您单击了"+bundle.getString("result2"));
        initView();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        transaction.replace(R.id.fragment,fragment1);
        //提交事务
        transaction.commit();
    }

    //初始化控件
    private void initView() {
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.textView1:
                transaction.replace(R.id.fragment,fragment1);
                break;
            case R.id.textView2:
                transaction.replace(R.id.fragment,fragment2);
                break;
            default:
                break;
        }
        transaction.commit();
    }
}

