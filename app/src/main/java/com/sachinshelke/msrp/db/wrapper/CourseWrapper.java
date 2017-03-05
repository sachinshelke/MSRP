package com.sachinshelke.msrp.db.wrapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sachinshelke.msrp.db.DbHelper;
import com.sachinshelke.msrp.db.Table;
import com.sachinshelke.msrp.models.CourseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachin K. Shelke on 05/03/17.
 */

public class CourseWrapper {

    Context context;
    DbHelper dbHelper;

    public CourseWrapper(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }


    public synchronized long addCourse(CourseModel courseModel) {
        SQLiteDatabase database;
        long rowId = -1;
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues cvs = new ContentValues();


            cvs.put(Table.Course.Cols.NAME, courseModel.getName());
            cvs.put(Table.Course.Cols.ABRV, courseModel.getAbbreviation());
            cvs.put(Table.Course.Cols.DURATION, courseModel.getDuration());

            rowId = database.insert(Table.Course.TABLE_NAME, null, cvs);


        } catch (Exception e) {
            e.printStackTrace();
            rowId = -1;
        }
        return rowId;
    }


    public synchronized long updateCourse(CourseModel courseModel) {
        SQLiteDatabase database;
        long rowId = 0;
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues cvs = new ContentValues();


            cvs.put(Table.Course.Cols.NAME, courseModel.getName());
            cvs.put(Table.Course.Cols.ABRV, courseModel.getAbbreviation());
            cvs.put(Table.Course.Cols.DURATION, courseModel.getDuration());
            rowId = database.update(Table.Course.TABLE_NAME, cvs, Table.Course._ID + " = " + courseModel.getId(), null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowId;
    }


    /**
     * @return course model list which is either empty or non empty. This function do not return any NULL value;
     */
    public synchronized List<CourseModel> getCourse() {

        List<CourseModel> courseModelList = new ArrayList<>();
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {


            database = dbHelper.getReadableDatabase();

            cursor = database.query(Table.Course.TABLE_NAME, null, null, null, null, null, null);

            if (cursor.getCount() == 0) {
                return courseModelList;
            }

            cursor.moveToFirst();

            do {
                CourseModel course = new CourseModel();

                course.parseCursor(cursor);
                courseModelList.add(course);


            } while (cursor.moveToNext());


        } catch (Exception e) {
            e.printStackTrace();
            courseModelList.clear();

        } finally {
            if (database != null) {
                database.close();
            }

            if (cursor != null)
                cursor.close();
        }


        return courseModelList;

    }


    /**
     * @return course model list which is either empty or non empty. This function do not return any NULL value;
     */
    public synchronized CourseModel getCourseById(int id) {

        CourseModel courseModel = null;
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {


            database = dbHelper.getReadableDatabase();

            cursor = database.query(
                    Table.Course.TABLE_NAME,
                    null,
                    Table.Course._ID + " = " + id + "",
                    null, null, null, null);

            if (cursor.getCount() == 0) {
                return courseModel;
            }

            cursor.moveToFirst();

            CourseModel course = new CourseModel();

            course.parseCursor(cursor);


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (database != null) {
                database.close();
            }

            if (cursor != null)
                cursor.close();
        }


        return courseModel;

    }


}
