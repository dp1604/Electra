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
    EditText topic,things,begin;
    Button update,delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_lesson);

        topic = findViewById(R.id.topic);
        things = findViewById(R.id.things);
        begin = findViewById(R.id.begin);

        update = findViewById(R.id.insert);
        DB = new DBHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTXT = topic.getText().toString();
                String thingsTXT = things.getText().toString();
                String beginTXT = begin.getText().toString();

                Boolean checkupdatedata = DB.updatelesson(topicTXT,thingsTXT,beginTXT);
                if(checkupdatedata==true)
                    Toast.makeText(ReadLesson.this, "Lesson Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ReadLesson.this, "Lesson not Updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTXT = topic.getText().toString();
                Boolean checkdeletedata = DB.deletelesson(topicTXT);
                if(checkdeletedata==true)
                    Toast.makeText(ReadLesson.this, "Lesson Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ReadLesson.this, "Lesson not Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getlesson();
                if (res.getCount() == 0) {
                    Toast.makeText(ReadLesson.this, "No lesson Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Topic:"+res.getString(0)+"\n");
                    buffer.append("Things:"+res.getString(1)+"\n");
                    buffer.append("Begin:"+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ReadLesson.this);
                builder.setCancelable(true);
                builder.setTitle("Lessons");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        });

    }
}