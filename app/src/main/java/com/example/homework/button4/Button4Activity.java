package com.example.homework.button4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.Main2Activity;
import com.example.homework.R;


public class Button4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button4_activity);
        Button button = (Button) findViewById(R.id.button41);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Button4Activity.this, "退出", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Button4Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
