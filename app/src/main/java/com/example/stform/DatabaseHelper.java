package com.example.stform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_name = "studentDB";
    public static final String TABLE_name = "students";
    public DatabaseHelper(Context context) {
        super(context, DB_name, null, 1);}
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE students ("+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT,"+"dept TEXT,"+"phone TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
    public Boolean insertStudent(
            String name,
            String dept,
            String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("dept",dept);
        cv.put("phone",phone);
        long result = db.insert(TABLE_name,null,cv);
        return result != -1;
    }
    public Cursor getStudents(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT*FROM students",null);
    }
    public Boolean updateStudent(
            String id,
            String name,
            String dept,
            String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("dept",dept);
        cv.put("phone",phone);
        db.update(TABLE_name,cv,"id=?",new String[]{id});
        return true;
    }
    public Integer deleteStudent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_name,"id=?",new String[]{id});
    }
}
