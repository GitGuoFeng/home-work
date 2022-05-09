package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homework.button1.Main3Activity;
import com.example.homework.button2.Main4Activity;
import com.example.homework.button3.Button3Activity;
import com.example.homework.button3.SerializableObject;
import com.example.homework.button4.Button4Activity;
import com.example.homework.button5.Button5Activity;

import static com.example.homework.R.array.ctype;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final ListView listView = (ListView)findViewById(R.id.listView);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,ctype,R.layout.support_simple_spinner_dropdown_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        String result1 = adapterView.getItemAtPosition(position).toString().substring(2,3);
                        Toast.makeText(Main2Activity.this,"您选择了第"+result1+"项",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(Main2Activity.this, Main3Activity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        String result2 = adapterView.getItemAtPosition(position).toString().substring(2,3);
                        Toast.makeText(Main2Activity.this,"您选择了第"+result2+"项",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Main2Activity.this, Main4Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("result2",result2);
                        intent2.putExtras(bundle);
                        startActivity(intent2);
                        break;
                    case 2:
                        String result3 = adapterView.getItemAtPosition(position).toString().substring(2,3);
                        Toast.makeText(Main2Activity.this,"您选择了第"+result3+"项",Toast.LENGTH_SHORT).show();
                        SerializableObject object = new SerializableObject();
                        object.setId(12);
                        object.setName("啊啊啊");
                        Intent intent3 = new Intent(Main2Activity.this, Button3Activity.class);
                        intent3.putExtra("int",123)
                                .putExtra("byte",1)
                                .putExtra("object",object);
                        startActivity(intent3);
                        break;
                    case 3:
                        String result4 = adapterView.getItemAtPosition(position).toString().substring(2,3);
                        Toast.makeText(Main2Activity.this,"您选择了第"+result4+"项",Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent(Main2Activity.this, Button4Activity.class);
                        startActivity(intent4);
                        break;
                    case 4:

                        break;
                    default:
                        break;
                }
            }
        });
    }
}

