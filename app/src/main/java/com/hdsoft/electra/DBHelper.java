package com.hdsoft.electra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
     public DBHelper(Context context) {
         super(context, "Lesson.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
         DB.execSQL("create Table Lesssons(topic TEXT primary key, things TEXT, description TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
         DB.execSQL("drop Table if exists Lessons");

    }

    public Boolean insertlesson(String topic,String things,String description){
         SQLiteDatabase DB = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("topic",topic);
         contentValues.put("things",things);
         contentValues.put("description",description);

        long result=DB.insert("Lessons",null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatelesson(String topic,String things,String description){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("things",things);
        contentValues.put("description",description);
        Cursor cursor = DB.rawQuery("Select * from Lessons where topic = ?", new String[] {topic});
        if(cursor.getCount()>0) {
            long result = DB.update("Lessons", contentValues, "topic=?", new String[]{topic});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletelesson(String topic){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lessons where topic = ?", new String[] {topic});
        if(cursor.getCount()>0) {
            long result = DB.delete("Lessons", "topic=?", new String[]{topic});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getlesson(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lessons ", null);
        return cursor;
    }



}
