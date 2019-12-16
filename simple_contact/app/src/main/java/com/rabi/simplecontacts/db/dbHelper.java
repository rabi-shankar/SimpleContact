package com.rabi.simplecontacts.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rabi.simplecontacts.model.Contact;
import com.rabi.simplecontacts.model.Message;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {

    private static final String TAG = "sqldb";
    private static final String DB_NAME = "message.db";
    private static final int DB_VERSION = 1;

    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MsgTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //no need now
    }


    public boolean setMessage(Message message) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MsgTable.COLUMN_NAME, message.getContact().getmFullName());
        contentValues.put(MsgTable.COLUMN_NUMBER, message.getContact().getmNumber());
        contentValues.put(MsgTable.COLUMN_OTP, message.getOTP());
        contentValues.put(MsgTable.COLUMN_TIMESTAMP, message.getTimestamp());

        return db.insert(MsgTable.TABLE_NAME, null, contentValues) > 0;
    }

    public boolean deleteMessage(Message message){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MsgTable.TABLE_NAME, MsgTable.COLUMN_NUMBER + "=" + "+91"+message.getContact().getmNumber(), null) > 0;
    }



    public void allDelete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ MsgTable.TABLE_NAME);
    }

    public ArrayList<Message> getAllMessage() {

        ArrayList<Message> messageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "SELECT * FROM  "+MsgTable.TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            Message message = new Message();
            Contact contact = new Contact();

            contact.setmFullName(res.getString(res.getColumnIndex(MsgTable.COLUMN_NAME)));
            contact.setmNumber(res.getString(res.getColumnIndex(MsgTable.COLUMN_NUMBER)));

            message.setContact(contact);
            message.setOTP(res.getString(res.getColumnIndex(MsgTable.COLUMN_OTP)));
            message.setTimestamp(res.getString(res.getColumnIndex(MsgTable.COLUMN_TIMESTAMP)));

            messageList.add(message);
            res.moveToNext();
        }
        res.close();
        return messageList;

    }



}
