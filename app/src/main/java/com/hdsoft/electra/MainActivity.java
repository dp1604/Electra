package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DBHandler myDb;
    EditText editComment;
    Button btnADD;


    private static final String TAG ="ele";

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DBHandler(this);

        editComment = (EditText)findViewById(R.id.editText_Cmment);
        btnADD =(Button) findViewById(R.id.button2_add);
        AddData();

        Log.i(TAG,"onCreate");
    }

    public void AddData(){
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean isInserted =  myDb.insertData(editComment.getText().toString());
                if (isInserted = true)
                    Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_LONG);
                else
                    Toast.makeText(MainActivity.this,"Data not Inserted", Toast.LENGTH_LONG);

            }
        });
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),"Comment Added Successfully",Toast.LENGTH_LONG);

        toast.show();
    }
}
