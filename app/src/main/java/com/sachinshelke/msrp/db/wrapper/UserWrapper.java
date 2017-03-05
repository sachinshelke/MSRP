package com.sachinshelke.msrp.db.wrapper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sachinshelke.msrp.db.DbHelper;
import com.sachinshelke.msrp.db.Table;

/**
 * Created by Sachin K. Shelke on 05/03/17.
 */


// where username = 'admin' and password = 'admin'
public class UserWrapper {

    Context context;
    DbHelper dbHelper;

    public UserWrapper(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }


    public synchronized boolean validateUser(String username, String password) {
        boolean isValidatedWithDb = true;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = db.query(Table.User.TABLE_NAME, null,
                    Table.User.Cols.USERNAME + " = ? AND " + Table.User.Cols.PASSWORD + " = ?",
                    new String[]{username, password}, null, null, null);

            if (cursor.getCount() > 0) {
                isValidatedWithDb = true;
            }


        } catch (Exception e) {
            e.printStackTrace();
            isValidatedWithDb = false;
        } finally {
            if (db != null) {
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }


        return isValidatedWithDb;
    }


}
