package com.example.lab2_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPerson;
    private EmployeeAdapter adapter;
    private List<Employee> employees = new ArrayList<>();
    private EditText tenNV;
    private EditText maNV;
    private CheckBox checkNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPerson = findViewById(R.id.rvPerson);
        adapter = new EmployeeAdapter(this, employees);
        rvPerson.setAdapter(adapter);
        rvPerson.setLayoutManager(new LinearLayoutManager(this));

        tenNV = findViewById(R.id.tenNV);
        maNV = findViewById(R.id.maNV);
        checkNV = findViewById(R.id.checkNV);

        Button buttonNhap = findViewById(R.id.buttonNhap);
        buttonNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tenNV.getText().toString();
                String id = maNV.getText().toString();
                boolean isManager = checkNV.isChecked();

                Employee newEmployee = new Employee(name, id, isManager);

                employees.add(newEmployee);

                adapter.notifyDataSetChanged();
            }
        });
    }
}
