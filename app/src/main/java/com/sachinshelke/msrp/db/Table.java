package com.sachinshelke.msrp.db;

/**
 * Created by Sachin K. Shelke on 05/03/17.
 */

public class Table {


    public static final String _ID = "_id";

    public static class User extends Table {


        public static final String TABLE_NAME = "tbl_User";

        public interface Cols {
            String USERNAME = "username";
            String PASSWORD = "password";
        }


    }


    public static class Course extends Table {
        public static final String TABLE_NAME = "tbl_Course";

        public interface Cols {
            String NAME = "course_name";
            String ABRV = "abbreviation";
            String DURATION = "duration";

        }
    }


}
