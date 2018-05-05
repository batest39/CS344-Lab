package com.example.batest39.myfirstapplication;

import android.provider.BaseColumns;

public class MyDbContract {
    public MyDbContract(){}


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = " , " ;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.TABLE_NAME + " ( " +
                    User._ID + " INTEGER PRIMARY KEY, " +
                    User.COLUMN_NAME_USER_ID + TEXT_TYPE
            + COMMA_SEP +
                    User.COLUMN_NAME_USERNAME + TEXT_TYPE +
                    User.COLUMN_NAME_PASSWORD + TEXT_TYPE
            + COMMA_SEP + " ) " ;


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.TABLE_NAME;

    //inner class that defines the schema
    public static abstract class User implements BaseColumns{
        public static final String TABLE_NAME = "userTable";
        public static final String COLUMN_NAME_USER_ID = "uid";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
