package com.example.user.databases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.solver.widgets.Helper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {


    ListView lv;
    SQLiteDatabase db;
    HelperDB hlp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
                     //if delete values is needed
                     //db.delete("User", null, null);
                     //db.delete("Contact", null, null);
        db.close();

        ListView lv = (ListView) findViewById(R.id.lv);


        ArrayList<String> theList = new ArrayList<>();
        Cursor contact = hlp.getContactContents();
        Cursor user = hlp.getUserContents();
        if (user.getCount() == 0) {
            Toast.makeText(this, "There are no contents in user info list!", Toast.LENGTH_LONG).show();
        } else {
            while (user.moveToNext())
                theList.add(user.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                lv.setAdapter(listAdapter);
            }
            user.moveToNext();


        if (contact.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (contact.moveToNext()) {
                theList.add(contact.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                lv.setAdapter(listAdapter);
            }
            contact.moveToNext();


        }
        contact.close();
        user.close();
    }
}
