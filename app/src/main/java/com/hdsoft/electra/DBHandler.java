package com.hdsoft.electra;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "Electra";
    public static final String TABLE_NAME = "Comments";

    //column names
    public static final String ID = "id";
    public static final String COMMENT = "comments";


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " + "("
              +ID+ "INTEGER PRIMARY KEY AUTOINCREMENT," +COMMENT+ "TEXT" + ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        // Drop order table if exists
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        // Create table again
        onCreate(sqLiteDatabase);
    }

}
