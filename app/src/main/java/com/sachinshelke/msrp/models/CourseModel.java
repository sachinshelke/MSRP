package com.sachinshelke.msrp.models;

import android.database.Cursor;

import com.sachinshelke.msrp.db.Table;

/**
 * Created by Sachin K. Shelke on 05/03/17.
 */

public class CourseModel {


    int id;
    String name;
    String abbreviation;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbrivation) {
        this.abbreviation = abbrivation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int duration;


    public void parseCursor(Cursor cursor) {
        setName(cursor.getString(cursor.getColumnIndex(Table.Course.Cols.NAME)));
        setAbbreviation(cursor.getString(cursor.getColumnIndex(Table.Course.Cols.ABRV)));
        setDuration(cursor.getInt(cursor.getColumnIndex(Table.Course.Cols.DURATION)));
        setId(cursor.getInt(cursor.getColumnIndex(Table.Course._ID)));
    }

}
