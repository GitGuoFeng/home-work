package com.example.homework.button5;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homework.R;

import java.util.ArrayList;
import java.util.List;

public class Button5Activity extends Activity {

    private final String[] columns = {"name","money"};
    private Uri uri = Uri.parse("content://com.example.homework.button5.billProvider");
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button5_activity);
        final EditText money = findViewById(R.id.money);
        final EditText name = findViewById(R.id.name);
        Button button = findViewById(R.id.submit);
        final ListView bill= findViewById(R.id.bill);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String m = money.getText().toString();
                list.clear();
//                add
                if (m.equals("") || n.equals("")){
                    Toast.makeText(Button5Activity.this, "请填写对听的输入框", Toast.LENGTH_SHORT).show();
                }else{
                    ContentResolver resolver = getContentResolver();
                    ContentValues contentValues = new ContentValues();

                    contentValues.put("name",n);
                    contentValues.put("money",m);
                    resolver.insert(uri,contentValues);
//                query
                    Cursor cursor = resolver.query(uri, null, null, null, null);
                    while (cursor.moveToNext()){
                        int columnIndex = cursor.getColumnIndex(columns[0]);
                        int columnIndex1 = cursor.getColumnIndex(columns[1]);
                        String string = cursor.getString(columnIndex);
                        String string1 = cursor.getString(columnIndex1);
                        list.add(string + "   花费    " + string1);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Button5Activity.this, android.R.layout.simple_list_item_1, list);
                    bill.setAdapter(arrayAdapter);
                    cursor.close();
                }
            }
        });
    }
}

