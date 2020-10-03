package com.hdsoft.electra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(comment TEXT primary key, contact TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");

    }

    public Boolean insertuserdata(String comment, String contact)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("comment",comment);
        contentValues.put("contact",contact);
        long result=DB.insert("Userdetails",null, contentValues);
        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Boolean updateuserdata(String comment, String contact)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("contact",contact);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where comment = ?", new String[] {comment});
        if (cursor.getCount()>0)
        {

            long result=DB.update("Userdetails", contentValues, "comment=?", new String[] {comment});
            if (result == -1) {
                return false;
            }else {
                return true;
            }
        }else
        {
            return false;
        }}


    public Boolean deletedata(String comment)
    {
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery("Select * from Userdetails where comment = ?", new String[] {comment});
        if (cursor.getCount()>0)
        {

            long result=DB.delete("Userdetails", "comment=?", new String[] {comment});
            if (result == -1) {
                return false;
            }else {
                return true;
            }
        }else
        {
            return false;
        }


    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);

        return cursor;
    }

}
