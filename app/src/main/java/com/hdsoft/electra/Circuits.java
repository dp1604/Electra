package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Circuits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuits);
        getSupportActionBar().hide();

        Button circuitListButton = findViewById(R.id.circuit_list_btn);
        circuitListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CircuitList.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
