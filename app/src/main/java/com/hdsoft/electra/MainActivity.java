package com.hdsoft.electra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView comment;
    Button insert, update, delete, view;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comment = findViewById(R.id.textView);

        insert = findViewById(R.id.btnAdd);
        update = findViewById(R.id.btnUPDATE);
        delete = findViewById(R.id.btnDELETE);
        view = findViewById(R.id.btnVIEW);
        DB = new DBHelper(this);

        //Insert Data
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentTXT = comment.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(commentTXT);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "Comment Added Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Comment Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

       //Update Data
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentTXT = comment.getText().toString();

                Boolean checkupdatetdata = DB.updateuserdata(commentTXT);
                if (checkupdatetdata == true)
                    Toast.makeText(MainActivity.this, "Comment Updated Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Comment Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

       //Delete Data
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentTXT = comment.getText().toString();

                Boolean checkdeletetdata = DB.deletedata(commentTXT);
                if (checkdeletetdata == true)
                    Toast.makeText(MainActivity.this, "Comment Deleted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Comment Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

       //View Data
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res =DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Comments to View", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Comment :"+res.getString( 0));
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Your Comment");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }


}
