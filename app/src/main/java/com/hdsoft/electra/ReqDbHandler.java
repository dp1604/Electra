package com.hdsoft.electra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReqDbHandler extends SQLiteOpenHelper {

    private static  final int VERSION = 1;
    private static  final String DB_NAME = "requests";
    private static  final String TABLE_NAME ="request";

    //columns
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CATEGORY = "category";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String PHOTO = "photo";

    public ReqDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE"+TABLE_NAME+" "+
                ")"
                +ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+ "TEXT,"
                +CATEGORY+ "TEXT,"
                +VALUE1+"TEXT,"
                +VALUE2+"TEXT,"
                +PHOTO+"TEXT,"+
                ");";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS"+ TABLE_NAME;
        db.execSQL(DROP_TABLE_QUERY); //Drop old table
        onCreate(db); //Create new table
    }

    public void addRequest(ReqModle reqModle){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,reqModle.getTitle());
        contentValues.put(CATEGORY,reqModle.getCategory());
        contentValues.put(VALUE1,reqModle.getValue1());
        contentValues.put(VALUE2,reqModle.getValue2());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues); //save table
        sqLiteDatabase.close(); // close table
    }

    // get all ReqModle

    public List<ReqModle> getAllReqModles(){

        List<ReqModle> reqModles = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM"+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                ReqModle reqModle = new ReqModle();

                reqModle.setId(cursor.getInt(0));
                reqModle.setTitle(cursor.getString(0));
                reqModle.setCategory(cursor.getString(0));
                reqModle.setValue1(cursor.getString(0));
                reqModle.setValue2(cursor.getString(0));

                reqModles.add(reqModle);
            }while (cursor.moveToNext());

        }
        return reqModles;

    }
}
