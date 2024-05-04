package com.example.lab3_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private final List<Student> students = new ArrayList<>();
    private AdapterView.OnItemClickListener listener;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student, position, listener);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public Student getStudentAtPosition(int position) {
        return students.get(position);
    }



    public void addStudent(Student student) {
        students.add(student);
        notifyItemInserted(students.size() - 1);
    }

    public void updateStudentByMSSV(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getMssv().equals(updatedStudent.getMssv())) {
                students.set(i, updatedStudent);
                notifyItemChanged(i);
                return; // Exit the method once the student is found and updated
            }
        }
    }



    public void deleteStudentByMSSV(String mssv) {
        for(int i = 0; i <  students.size(); i++) {
            if(students.get(i).getMssv().equals(mssv)) {
                students.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
}
