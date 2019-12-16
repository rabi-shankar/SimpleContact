package com.rabi.simplecontacts.db;

public interface MsgTable {
    String TABLE_NAME = "message";
    String COLUMN_ID = "_id";
    String COLUMN_NAME = "contact_name";
    String COLUMN_NUMBER = "contact_number";
    String COLUMN_TIMESTAMP = "timestamp";
    String COLUMN_OTP = "otp";


    String CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_NUMBER + " TEXT NOT NULL, "
            + COLUMN_TIMESTAMP + " TEXT NOT NULL, "
            + COLUMN_OTP + " TEXT NOT NULL);";
}
