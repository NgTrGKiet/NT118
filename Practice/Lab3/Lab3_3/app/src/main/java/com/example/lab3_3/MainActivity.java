package com.example.lab3_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextLop;
    private EditText editTextMSSV;
    private StudentAdapter studentAdapter;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.hoten);
        editTextLop = findViewById(R.id.lop);
        editTextMSSV = findViewById(R.id.mssv);

        Button buttonAdd = findViewById(R.id.insert);
        Button buttonDelete = findViewById(R.id.delete);
        Button buttonUpdate = findViewById(R.id.update);
        Button buttonQuery = findViewById(R.id.query);

        RecyclerView recyclerViewStudents = findViewById(R.id.recycleView);

        studentAdapter = new StudentAdapter();
        recyclerViewStudents.setAdapter(studentAdapter);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));

        studentAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentAdapter.getStudentAtPosition(position);
                editTextLop.setText(student.getLop());
                editTextMSSV.setText(student.getMssv());
                editTextName.setText(student.getHoTen());
            }
        });

        DBHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM students", null);
        if (cursor.moveToFirst()) {
            do {
                String MSSV = cursor.getString(0);
                String Lop = cursor.getString(1);
                String HoTen = cursor.getString(2);
                Student student = new Student(MSSV, HoTen, Lop);
                studentAdapter.addStudent(student);
            } while (cursor.moveToNext());
        }
        cursor.close();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoTen = String.valueOf(editTextName.getText());
                String lop = String.valueOf(editTextLop.getText());
                String mssv = String.valueOf(editTextMSSV.getText());


                Student student = new Student(mssv,hoTen, lop);

                dbHelper.insertStudent(student);
                studentAdapter.addStudent(student);
                clearInputField();

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = String.valueOf(editTextMSSV.getText());

                dbHelper.deleteStudent(mssv);
                studentAdapter.deleteStudentByMSSV(mssv);
                clearInputField();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = String.valueOf(editTextName.getText());
                String lop = String.valueOf(editTextLop.getText());
                String mssv = String.valueOf(editTextMSSV.getText());
                Student student = new Student(mssv, hoTen, lop);

                studentAdapter.updateStudentByMSSV(student);
                dbHelper.updateStudent(student);
            }
        });

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = String.valueOf(editTextMSSV.getText());
                Cursor cursor = dbHelper.queryStudent(mssv);
                if (cursor.moveToNext()) {
                    String MSSV = cursor.getString(0);
                    String Lop = cursor.getString(1);
                    String HoTen = cursor.getString(2);

                    editTextLop.setText(Lop);
                    editTextMSSV.setText(MSSV);
                    editTextName.setText(HoTen);
                }
                cursor.close();
            }
        });

    }

    public void clearInputField() {
        editTextName.setText("");
        editTextMSSV.setText("");
        editTextLop.setText("");
    }
}