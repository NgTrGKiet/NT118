package com.example.lab2_3.model;

public class EmployeeParttime extends Employee{
    @Override
    public double tinhLuong() {return 150;}
    @Override
    public String toString() {return super.toString()+ "--> PartTime = "+ tinhLuong();}
}
