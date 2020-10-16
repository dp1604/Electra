package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LessonActivity extends AppCompatActivity {
    EditText topic,things,description;
    Button button,insert,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        button = (Button) findViewById(R.id.btnYes);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonActivity.this,ReadLesson.class);
                startActivity(intent);
            }
        });

        topic = findViewById(R.id.topic);
        things = findViewById(R.id.things);
        description = findViewById(R.id.description);

        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTXT = topic.getText().toString();
                String thingsTXT = things.getText().toString();
                String descriptionTXT = description.getText().toString();

                Boolean checkinsertdata = DB.insertlesson(topicTXT,thingsTXT,descriptionTXT);
                if(checkinsertdata==true)
                    Toast.makeText(LessonActivity.this, "Lesson Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LessonActivity.this, "Lesson not Inserted", Toast.LENGTH_SHORT).show();



            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getlesson();
                if (res.getCount()==0){
                    Toast.makeText(LessonActivity.this, "No Lesson Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Topic:"+res.getString(0)+"\n");
                    buffer.append("Things:"+res.getString(1)+"\n");
                    buffer.append("Description:"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(LessonActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Lessons");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });





    }
}