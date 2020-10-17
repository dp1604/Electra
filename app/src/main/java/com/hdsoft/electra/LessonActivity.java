package com.hdsoft.electra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LessonActivity extends AppCompatActivity {
    EditText topic,prop,descrip;
    Button insert,update,delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);


        topic = findViewById(R.id.topic);
        prop = findViewById(R.id.prop);
        descrip = findViewById(R.id.descrip);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTXT = topic.getText().toString();
                String propTXT = prop.getText().toString();
                String descripTXT = descrip.getText().toString();

                Boolean checkinsertdata = DB.insertlessondata(topicTXT, propTXT, descripTXT);
                if (checkinsertdata == true)
                    Toast.makeText(LessonActivity.this, "New Lesson Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LessonActivity.this, "New Lesson Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTXT = topic.getText().toString();
                String propTXT = prop.getText().toString();
                String descripTXT = descrip.getText().toString();

                Boolean checkupdatedata = DB.updatelessondata(topicTXT, propTXT, descripTXT);
                if (checkupdatedata == true)
                    Toast.makeText(LessonActivity.this, "Lesson Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LessonActivity.this, "Lesson Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTXT = topic.getText().toString();
                Boolean checkdeletedata = DB.deletedata(topicTXT);
                if (checkdeletedata == true)
                    Toast.makeText(LessonActivity.this, "Lesson Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LessonActivity.this, "Lesson Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(LessonActivity.this, "No Lesson Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Topic:" + res.getString(0) + "\n");
                    buffer.append("Things want:" + res.getString(1) + "\n");
                    buffer.append("Description:" + res.getString(2) + "\n\n");
                }

                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(LessonActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}