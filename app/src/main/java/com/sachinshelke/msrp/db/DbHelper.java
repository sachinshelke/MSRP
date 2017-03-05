package com.sachinshelke.msrp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Sachin K. Shelke on 05/03/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MSRP.db";
    private static final int DB_VERSION = 2;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableVersion1(db);
        update1to2(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        update1to2(db);

    }


    private void createTableVersion1(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table.User.TABLE_NAME + " ("
                + Table.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Table.User.Cols.USERNAME + " TEXT UNIQUE,"
                + Table.User.Cols.PASSWORD + " TEXT)");


        ContentValues cvs = new ContentValues();
        cvs.put(Table.User.Cols.USERNAME, "admin");
        cvs.put(Table.User.Cols.PASSWORD, "admin");

        db.insert(Table.User.TABLE_NAME, null, cvs);
    }


    private void update1to2(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table.Course.TABLE_NAME + " ("
                + Table.Course._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Table.Course.Cols.NAME + " TEXT,"
                + Table.Course.Cols.ABRV + " TEXT,"
                + Table.Course.Cols.DURATION + " INTEGER)");
    }


}
