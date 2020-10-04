package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class TheCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_calculator);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        final String circuitId = intent.getStringExtra("circuitId");
        final String resultLabel = intent.getStringExtra("result");

        final TextView resultLabelTextView = findViewById(R.id.result);
        resultLabelTextView.setText(resultLabel+" :");

        final CalculatorEngine engine = new CalculatorEngine();

        final DataField[] myListData = engine.getRequiredFieldsList(Integer.parseInt(circuitId));

        /*DataField[] myListData = new DataField[] {
                new DataField(1,"voltage",230.0),
                new DataField(1,"resistance",1000.0)
        };*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fields);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button calcBtn = findViewById(R.id.calculate);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //engine.calculate((Integer.parseInt(circuitId)), myListData);
                resultLabelTextView.setText(resultLabel+" : "+engine.calculate((Integer.parseInt(circuitId)), myListData));
            }
        });

        Button savePresetBtn = findViewById(R.id.save_to_presets);
        final DatabaseHandler db = new DatabaseHandler(this);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (DataField dataField : myListData) {
                    Date now = new Date();
                    dataField.setTimestamp("" + now.getTime() + "");
                    db.addDataField(dataField);
                }
            }
        });
    }
}
