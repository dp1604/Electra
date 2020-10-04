package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CircuitList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuit_list);
        getSupportActionBar().hide();

        Button ohmsLawButton = findViewById(R.id.ohms_law_btn);
        ohmsLawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TheCalculator.class);
                intent.putExtra("circuitId", "1");// example : 1 = ohm's law
                intent.putExtra("result", "Current");// example : Current = ohm's law

                view.getContext().startActivity(intent);
            }
        });
    }
}
