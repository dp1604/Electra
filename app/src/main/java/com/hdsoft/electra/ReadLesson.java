package com.hdsoft.electra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReadLesson extends AppCompatActivity{

    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_lesson);

        button = (Button) findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadLesson.this,ReadLesson.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadLesson.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
