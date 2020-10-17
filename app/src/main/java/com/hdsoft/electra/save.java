package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.widget.Toast;

import java.util.List;

public class save extends AppCompatActivity {

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        getSupportActionBar().hide();

        builder = new AlertDialog.Builder(this);

        final Button ohmsLawPresetBtn = findViewById(R.id.ohms_law_preset_btn);
        final Button oscillator555PresetBtn = findViewById(R.id.oscillator_555_preset_btn);
        final Button colpittsOscillatorPresetBtn = findViewById(R.id.colpitts_oscillator_preset_btn);

        final Button delOhmsLawPresetBtn = findViewById(R.id.del_ohms_law_preset_btn);
        final Button delOscillator555PresetBtn = findViewById(R.id.del_oscillator_555_preset_btn);
        final Button delColpittsOscillatorPresetBtn = findViewById(R.id.del_colpitts_oscillator_preset_btn);

        ohmsLawPresetBtn.setVisibility(View.GONE);
        oscillator555PresetBtn.setVisibility(View.GONE);
        colpittsOscillatorPresetBtn.setVisibility(View.GONE);

        delOhmsLawPresetBtn.setVisibility(View.GONE);
        delOscillator555PresetBtn.setVisibility(View.GONE);
        delColpittsOscillatorPresetBtn.setVisibility(View.GONE);

        final DatabaseHandler db = new DatabaseHandler(this);
        final List<DataField> dataFieldsList = db.getAllDataFields();

        for (int i = 0; i < dataFieldsList.size(); i++) {
            final int loop = i;
            if(dataFieldsList.get(i).getCircuitId() == 1){
                ohmsLawPresetBtn.setVisibility(View.VISIBLE);
                delOhmsLawPresetBtn.setVisibility(View.VISIBLE);
                delOhmsLawPresetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.setMessage("Are you sure you want to delete this preset ?")
                                .setTitle("Confirm Delete")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                        try{
                                            db.deleteDataField(dataFieldsList.get(loop));
                                        } catch(Exception e){
                                            e.printStackTrace();
                                        }
                                        ohmsLawPresetBtn.setVisibility(View.GONE);
                                        delOhmsLawPresetBtn.setVisibility(View.GONE);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Toast.makeText(getApplicationContext(),"Preset not deleted",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Confirm Delete");
                        alert.show();
                    }
                });
            } else if(dataFieldsList.get(i).getCircuitId() == 2){
                oscillator555PresetBtn.setVisibility(View.VISIBLE);
                delOscillator555PresetBtn.setVisibility(View.VISIBLE);
                delOscillator555PresetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.setMessage("Are you sure you want to delete this preset ?")
                                .setTitle("Confirm Delete")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                        try{
                                            db.deleteDataField(dataFieldsList.get(loop));
                                        } catch(Exception e){
                                            e.printStackTrace();
                                        }
                                        oscillator555PresetBtn.setVisibility(View.GONE);
                                        delOscillator555PresetBtn.setVisibility(View.GONE);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Toast.makeText(getApplicationContext(),"Preset not deleted",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Confirm Delete");
                        alert.show();
                    }
                });
            } else if(dataFieldsList.get(i).getCircuitId() == 3){
                colpittsOscillatorPresetBtn.setVisibility(View.VISIBLE);
                delColpittsOscillatorPresetBtn.setVisibility(View.VISIBLE);
                delColpittsOscillatorPresetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.setMessage("Are you sure you want to delete this preset ?")
                                .setTitle("Confirm Delete")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                        try{
                                            db.deleteDataField(dataFieldsList.get(loop));
                                        } catch(Exception e){
                                            e.printStackTrace();
                                        }
                                        colpittsOscillatorPresetBtn.setVisibility(View.GONE);
                                        delColpittsOscillatorPresetBtn.setVisibility(View.GONE);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        Toast.makeText(getApplicationContext(),"Preset not deleted",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Confirm Delete");
                        alert.show();
                    }
                });
            }
        }



        ohmsLawPresetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TheCalculator.class);
                intent.putExtra("circuitId", "1");// example : 1 = ohm's law
                intent.putExtra("result", "Current (mA)");// example : Current = ohm's law
                intent.putExtra("isPreset","yes");// yes or no

                view.getContext().startActivity(intent);
            }
        });

        oscillator555PresetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TheCalculator.class);
                intent.putExtra("circuitId", "2");// example : 1 = ohm's law
                intent.putExtra("result", "Frequency (Hz)");// example : Current = ohm's law
                intent.putExtra("isPreset","yes");// yes or no

                view.getContext().startActivity(intent);
            }
        });

        colpittsOscillatorPresetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TheCalculator.class);
                intent.putExtra("circuitId", "3");// example : 1 = ohm's law
                intent.putExtra("result", "Frequency (Hz)");// example : Current = ohm's law
                intent.putExtra("isPreset","yes");// yes or no

                view.getContext().startActivity(intent);
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
