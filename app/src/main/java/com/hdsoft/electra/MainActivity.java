package com.hdsoft.electra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText comment, contact;
    Button insert, update, delete, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comment = findViewById(R.id.comment);
        contact = findViewById(R.id.contact);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentTXT = comment.getText() .toString();
                String contactTXT = contact.getText() .toString();

                Boolean checkinsertdata = DB.insertuserdata(commentTXT,contactTXT);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Comment Added Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Comment Not Added", Toast.LENGTH_SHORT).show();


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentTXT = comment.getText() .toString();
                String contactTXT = contact.getText() .toString();

                Boolean checkupdatedata = DB.updateuserdata(commentTXT,contactTXT);
                if (checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Comment Updated Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Comment Not Updated", Toast.LENGTH_SHORT).show();


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentTXT = comment.getText() .toString();

                Boolean checkdeletedata = DB.deletedata(commentTXT);
                if (checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "Comment Deleted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Comment Not Deleted", Toast.LENGTH_SHORT).show();


            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0) {
                    Toast.makeText(MainActivity.this, "No Comments", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Comment :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n\n");
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