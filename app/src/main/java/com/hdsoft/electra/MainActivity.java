package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_topic,editText_things,editText_begin;
    Button button_add,button_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_topic = findViewById(R.id.editText_topic);
        editText_things = findViewById(R.id.editText_things);
        editText_begin = findViewById(R.id.editText_begin);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringTopic = editText_topic.getText().toString();
                String stringThings = editText_things.getText().toString();
                String stringBegin = editText_begin.getText().toString();

                if (stringTopic.length() <=0 || stringThings.length() <=0 || stringBegin.length() <=0){
                    Toast.makeText(MainActivity.this, "You should enter all data", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
