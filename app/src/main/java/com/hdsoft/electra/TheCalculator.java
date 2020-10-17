package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class TheCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_calculator);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        final String circuitId = intent.getStringExtra("circuitId");
        final String resultLabel = intent.getStringExtra("result");
        final String isPreset = intent.getStringExtra("isPreset");

        final TextView resultLabelTextView = findViewById(R.id.result);
        resultLabelTextView.setText(resultLabel+" :");

        final CalculatorEngine engine = new CalculatorEngine();
        final DataField[] myListData;

        if(isPreset.equals("yes")){
            DatabaseHandler db = new DatabaseHandler(this);
            List<DataField> dataFieldsList = db.getAllDataFields();

            for (int i = 0; i < dataFieldsList.size(); i++) {
                if(dataFieldsList.get(i).getCircuitId() != Integer.parseInt(circuitId)){
                    dataFieldsList.remove(i);
                    i--;
                }
            }

            DataField[] tempMyListData = new DataField[dataFieldsList.size()];
            for (int i = 0; i < dataFieldsList.size(); i++) {
                tempMyListData[i] = dataFieldsList.get(i);
            }

            myListData = tempMyListData;

        } else{
            myListData = engine.getRequiredFieldsList(Integer.parseInt(circuitId));
        }

        RecyclerView recyclerView = findViewById(R.id.fields);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button calcBtn = findViewById(R.id.calculate);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultLabelTextView.setText(resultLabel+" : "+engine.calculate((Integer.parseInt(circuitId)), myListData));
            }
        });

        Button savePresetBtn = findViewById(R.id.save_to_presets);
        final DatabaseHandler db = new DatabaseHandler(this);
        savePresetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    db.deleteDataField(myListData[0]); // Delete old entries
                } catch(Exception e){
                    e.printStackTrace();
                }
                for (DataField dataField : myListData) {
                    Date now = new Date();
                    dataField.setTimestamp("" + now.getTime() + "");
                    db.addDataField(dataField);
                }
                Toast.makeText(getApplicationContext(),"Preset Saved",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Home.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
