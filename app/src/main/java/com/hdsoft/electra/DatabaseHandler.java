package com.hdsoft.electra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "electra";
    private static final String TABLE_DATA_FIELDS = "dataFields";
    private static final String KEY_ID = "id";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_CIRCUIT_ID = "circuitId";
    private static final String KEY_KEY = "keyName";
    private static final String KEY_VALUE = "value";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATA_FIELDS_TABLE = "CREATE TABLE " + TABLE_DATA_FIELDS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_TIMESTAMP + " TEXT ," + KEY_CIRCUIT_ID + " TEXT," + KEY_KEY + " TEXT,"
                + KEY_VALUE + " TEXT" + ")";
        db.execSQL(CREATE_DATA_FIELDS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA_FIELDS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addDataField(DataField dataField) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIMESTAMP, dataField.getTimestamp());
        values.put(KEY_CIRCUIT_ID, (""+dataField.getCircuitId()+""));
        values.put(KEY_KEY, dataField.getKey());
        values.put(KEY_VALUE, (""+dataField.getValue()+""));

        // Inserting Row
        db.insert(TABLE_DATA_FIELDS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    DataField[] dataFields(String timestamp) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATA_FIELDS, new String[] { KEY_ID,
                        KEY_TIMESTAMP, KEY_CIRCUIT_ID, KEY_KEY, KEY_VALUE }, KEY_TIMESTAMP + "=?",
                new String[] { String.valueOf(timestamp) }, null, null, null, null);

        DataField[] dataFields;
        DataField dataField = new DataField();

        if (cursor != null) {
            cursor.moveToFirst();
            dataField = new DataField(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Double.parseDouble(cursor.getString(4)));
        }

        int x = 0;
        dataFields = new DataField[cursor.getCount()];
        dataFields[x] = dataField;

        do{
            x++;
            cursor.moveToNext();
            dataField = new DataField(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Double.parseDouble(cursor.getString(4)));
            dataFields[x] = dataField;
        }while(!cursor.isLast());

        return dataFields;
    }

    // code to get all contacts in a list view
    public List<DataField> getAllDataFields() {
        List<DataField> dataFields = new ArrayList<DataField>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA_FIELDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataField dataField = new DataField();
                dataField.setTimestamp(cursor.getString(1));
                dataField.setCircuitId(Integer.parseInt(cursor.getString(2)));
                dataField.setKey(cursor.getString(3));
                dataField.setValue(Double.parseDouble(cursor.getString(4)));
                dataFields.add(dataField);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataFields;
    }

    // code to update the single contact
    public int updateDataField(DataField dataField) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VALUE, dataField.getValue());

        // updating row
        return db.update(TABLE_DATA_FIELDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(dataField.getId()) });
    }

    // Deleting single contact
    public void deleteDataField(DataField dataField) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA_FIELDS, KEY_ID + " = ?",
                new String[] { String.valueOf(dataField.getId()) });
        db.close();
    }

    // Getting contacts Count
    public int getDataFieldsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATA_FIELDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}
