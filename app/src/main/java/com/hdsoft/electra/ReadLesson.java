package com.hdsoft.electra;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ReadLesson extends AppCompatActivity{
    EditText topic2,things2,description2;
    Button update,delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_lesson);

        topic2 = findViewById(R.id.topic2);
        things2 = findViewById(R.id.things2);
        description2 = findViewById(R.id.description2);

        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        DB = new DBHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic2TXT = topic2.getText().toString();
                String things2TXT = things2.getText().toString();
                String description2TXT = description2.getText().toString();

                Boolean checkupdatedata = DB.updatelesson(topic2TXT,things2TXT,description2TXT);
                if(checkupdatedata==true)
                    Toast.makeText(ReadLesson.this, "Lesson Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ReadLesson.this, "Lesson not Updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic2TXT = topic2.getText().toString();
                Boolean checkdeletedata = DB.deletelesson(topic2TXT);
                if(checkdeletedata==true)
                    Toast.makeText(ReadLesson.this, "Lesson Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ReadLesson.this, "Lesson not Deleted", Toast.LENGTH_SHORT).show();

            }
        });



    }
}