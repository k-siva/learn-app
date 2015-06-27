package com.example.asiablr.databasebasics;


import android.provider.BaseColumns;

public final class FeedReaderContract {

    // prevent instantiating the contract class
    public FeedReaderContract() {}

    // inner class that defines the table
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "EmpDetails";
        public static final String COLUMN_EMP_NAME = "EmpName";
        public static final String COLUMN_EMP_AGE = "EmpAge";
        public static final String COLUMN_EMP_ID = "EmpID";

    }


}
