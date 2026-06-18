package com.example.stform;

import android.os.Bundle;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    EditText eid,ename,edepartment,ephone;
    Button insert,read,update,delete;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eid = findViewById(R.id.eid);
        ename = findViewById(R.id.ename);
        edepartment = findViewById(R.id.edepartment);
        ephone = findViewById(R.id.ephone);
        insert = findViewById(R.id.insert);
        read = findViewById(R.id.read);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        db = new DatabaseHelper(this);
        insert.setOnClickListener(v->{
            Boolean inserted =db.insertStudent(ename.getText().toString(),edepartment.getText().toString(),ephone.getText().toString());
            if (inserted){
                Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(v->{
            Cursor res = db.getStudents();
            StringBuilder data = new stringBuilder();
            while (res.moveToNext()){
                data.append("ID : ")
                        .append(res.getString(0))
                        .append("\n");
                data.append("Name : ")
                        .append(res.getString(1))
                        .append("\n");
                data.append("Department : ")
                        .append(res.getString(2))
                        .append("\n");
                data.append("Phone : ")
                        .append(res.getString(3))
                        .append("\n\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Students");
            builder.setMessage(data.toString());
            builder.show();
        });
        update.setOnClickListener(v->{
            db.updateStudent(
                    eid.getText().toString(),ename.getText().toString(),edepartment.getText().toString(),ephone.getText().toString());
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        });
        delete.setOnClickListener(v->{
            db.deleteStudent(
                    eid.getText().toString());
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        });
    }
}
