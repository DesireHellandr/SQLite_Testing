package com.example.sqlite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLoadDB(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("testing.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists users (surname TEXT, name TEXT, age INTEGER)");

        db.execSQL("insert or ignore into users (surname, name, age) values ('Zaycev', 'Ivan', 23),('Reklin', 'Dmitriy', 19);");

        Cursor query = db.rawQuery("select * from users;", null);

        TextView dataDB = findViewById(R.id.dataDB);

        dataDB.setText("");
        while(query.moveToNext()) {
            String surname = query.getString(0);
            String name = query.getString(1);
            int age = query.getInt(2);
            dataDB.append("Surname: " + surname + " Name: " + name + " Age : " + age + "\n");
        }
        query.close();
        db.close();
    }
}