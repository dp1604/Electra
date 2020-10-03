package com.hdsoft.electra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addRequest extends AppCompatActivity {

    private Button btnReq;
    private EditText editTextTitle ,cate, val1,val2;
    private ReqDbHandler reqDbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_request);

        btnReq = (Button) findViewById(R.id.btnReq);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        cate = (EditText) findViewById(R.id.editTextCategory);
        val1 = (EditText) findViewById(R.id.editTextValue1);
        val2 = (EditText) findViewById(R.id.editTextValue2);

        context =this;

        reqDbHandler = new ReqDbHandler(context);

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(addRequest.this,requested_list.class);
                startActivity(i);


            }
        });
        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextTitle.getText().toString())){
                    Toast.makeText(addRequest.this,"No empty field allowed..",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(addRequest.this, editTextTitle.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userTitle = editTextTitle.getText().toString();
                String userCate = cate.getText().toString();
                String userVal1 = val1.getText().toString();
                String userVal2 = val2.getText().toString();


                ReqModle reqModle = new ReqModle(userTitle,userCate,userVal1,userVal2);
                reqDbHandler.addRequest(reqModle);
            }
        });
    }
}
