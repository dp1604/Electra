package com.hdsoft.electra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Lessondata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Lessondetails(topic TEXT primary key, prop TEXT, descrip TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Lessondetails");

    }

    public Boolean insertlessondata(String topic,String prop,String descrip){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("topic",topic);
        contentValues.put("prop",prop);
        contentValues.put("descrip",descrip);
        long result=DB.insert("Lessondetails",null,contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }


    }
    public Boolean updatelessondata(String topic,String prop,String descrip){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("prop",prop);
        contentValues.put("descrip",descrip);
        Cursor cursor = DB.rawQuery("Select * from Lessondetails where topic = ?",new String[]{topic});
        if (cursor.getCount()>0) {
            long result = DB.update("Lessondetails", contentValues, "topic=?", new String[]{topic});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }

    }

    public Boolean deletedata(String topic){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lessondetails where topic = ?",new String[]{topic});
        if (cursor.getCount()>0) {
            long result = DB.delete("Lessondetails", "topic=?", new String[]{topic});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }

    }

    public Cursor getdata(){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lessondetails",null);
        return cursor;

    }
}

