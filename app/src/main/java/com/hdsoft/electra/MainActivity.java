package com.hdsoft.electra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

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


        Log.i(TAG,"onCreate");
    }



    public void showToast(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),"Comment Added Successfully",Toast.LENGTH_LONG);

        toast.show();
    }
}
