package com.example.lab2_3.model;

public class EmployeeFulltime extends Employee{
    @Override
    public double tinhLuong() {return 500;}
    @Override
    public String toString() {return super.toString() + "--> FullTime = "+ tinhLuong();}
}
