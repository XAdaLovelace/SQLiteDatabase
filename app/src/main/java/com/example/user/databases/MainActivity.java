package com.example.user.databases;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button getUser;
    Button getContact;

    HelperDB hlp;

    EditText phoneNum, eMail, idNum, userName, userAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);

        getUser = (Button) findViewById(R.id.btnUser);
        getContact = (Button) findViewById(R.id.btnContact);

        final EditText phoneNum = (EditText) findViewById(R.id.phoneNum);
        final EditText eMail = (EditText) findViewById(R.id.eMail);
        final EditText idNum = (EditText) findViewById(R.id.idNum);
        final EditText userName = (EditText) findViewById(R.id.userName);
        final EditText userAge = (EditText) findViewById(R.id.userAge);

        getContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPhone = phoneNum.getText().toString();
                String email = eMail.getText().toString();
                String id = idNum.getText().toString();

                String temp = ""+newPhone+""+email+""+id+"";
                AddData(temp);

            }
        });

    }



    public void AddData(String temp){

        boolean insertData = hlp.addData(temp);

        if(insertData){
            Toast.makeText(this, "Successful.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Bad input.", Toast.LENGTH_LONG).show();
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.SecondActivity:
                Intent i=new Intent(this,SecondActivity.class);
                startActivity(i);
                break;
            case R.id.credits:
                Toast.makeText(MainActivity.this,
                        "This application was created by Shoam Alaluf", Toast.LENGTH_LONG).show();
                break;
        }

        return true;

    }
}
