package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button)findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = (EditText)findViewById(R.id.passWord);
                EditText username = (EditText)findViewById(R.id.userName);
                String name = username.getText().toString();
                String passwd = password.getText().toString();
                /*if(name.equals("admin") && passwd.equals("123456")){
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"请输入正确的账号密码",Toast.LENGTH_SHORT).show();

                }*/
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = (EditText)findViewById(R.id.passWord);
                EditText username = (EditText)findViewById(R.id.userName);
                username.setText("");
                password.setText("");
            }
        });
    }
}
