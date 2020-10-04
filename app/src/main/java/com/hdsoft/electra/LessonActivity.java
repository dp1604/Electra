package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LessonActivity extends AppCompatActivity {
    EditText topic,things,begin;
    Button insert,button;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        button = (Button) findViewById(R.id.more);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonActivity.this,ReadLesson.class);
                startActivity(intent);
            }
        });

        topic = findViewById(R.id.topic);
        things = findViewById(R.id.things);
        begin = findViewById(R.id.begin);

        insert = findViewById(R.id.insert);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTXT = topic.getText().toString();
                String thingsTXT = things.getText().toString();
                String beginTXT = begin.getText().toString();

                Boolean checkinsertdata = DB.insertlesson(topicTXT,thingsTXT,beginTXT);
                if(checkinsertdata==true)
                    Toast.makeText(LessonActivity.this, "Lesson Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LessonActivity.this, "Lesson not Inserted", Toast.LENGTH_SHORT).show();



            }
        });


    }
}