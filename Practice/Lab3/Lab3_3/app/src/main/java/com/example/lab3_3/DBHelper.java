package com.example.lab3_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 3;

    private static final String SQL_CREATE_TABLE_STUDENTS =
            "CREATE TABLE students (" +
                    "MSSV TEXT PRIMARY KEY," +
                    "Lop TEXT," +
                    "HoTen TEXT)";
    private static final String SQL_DELETE_TABLE_STUDENTS =
            "DROP TABLE IF EXISTS students";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_STUDENTS);
        onCreate(db);
    }

    public boolean insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MSSV", student.getMssv());
        values.put("Lop", student.getLop());
        values.put("HoTen", student.getHoTen());

        long result = db.insert("students", null, values);
        db.close();
        return result!=-1;
    }

    public boolean deleteStudent(String mssv) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("students", "MSSV = ?", new String[]{mssv});
        db.close();
        return result > 0;
    }

    public boolean updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Lop", student.getLop());
        values.put("HoTen", student.getHoTen());
        int result = db.update("students", values, "MSSV = ?", new String[]{student.getMssv()});
        db.close();
        return result > 0;
    }

    public Cursor queryStudent(String mssv) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("students", null, "MSSV = ?", new String[]{mssv}, null, null, null);
    }
}
