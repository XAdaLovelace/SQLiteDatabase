package com.example.user.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.user.databases.Contact.EMAIL;
import static com.example.user.databases.Contact.IDENTITY;
import static com.example.user.databases.Contact.PHONE;
import static com.example.user.databases.User.AGE;
import static com.example.user.databases.User.KEY_ID;
import static com.example.user.databases.User.NAME;

public class HelperDB extends SQLiteOpenHelper{

    public static final String TABLE_CONTACT="Contact";
    public static final String TABLE_USER = "User";

    String strCreate, strDelete;

    private static final String DATABASE_NAME = "db.db";
    private static final int DATABASE_VERSION = 1;

    public HelperDB(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_USER;
        strCreate+= " (_id INTEGER PRIMARY KEY,";
        strCreate+= " "+NAME+" TEXT,";
        strCreate+= " "+AGE+" INTEGER";
        strCreate+= ");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_CONTACT;
        strCreate+= " (_id INTEGER PRIMARY KEY,";
        strCreate+=" "+PHONE+" TEXT,";
        strCreate+=" "+EMAIL+" TEXT,";
        strCreate+=" "+IDENTITY+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        strDelete=("delete from "+ TABLE_USER);
        //strDelete="DROP TABLE IF EXISTS "+TABLE_USER;
        db.execSQL(strDelete);
        strDelete=("delete from "+ TABLE_CONTACT);
        db.execSQL(strDelete);

        onCreate(db);
    }
    ContentValues contactContentValues = new ContentValues();
    public boolean addContactData(String temp) {
        SQLiteDatabase db = this.getWritableDatabase();
        contactContentValues.put(PHONE, temp);

        long result1 = db.insert(TABLE_CONTACT, null, contactContentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;
        }
    }
    ContentValues userContentValues = new ContentValues();
    public boolean addUserData(String temp) {
        SQLiteDatabase db = this.getWritableDatabase();
        userContentValues.put(NAME, temp);

        long result2 = db.insert(TABLE_USER, null, userContentValues);

        if (result2 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getUserContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data1 = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        return data1;
    }
    public Cursor getContactContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_CONTACT, null);
        return data;
    }
}
