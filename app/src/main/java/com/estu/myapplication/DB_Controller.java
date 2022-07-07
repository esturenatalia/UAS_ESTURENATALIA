package com.estu.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE MAHASISWA(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT UNIQUE, NIM TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MAHASISWA;");
        onCreate(sqLiteDatabase);
    }

    public void insert_mahasiswa(String nama, String nim){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("NIM", nim);
        this.getWritableDatabase().insertOrThrow("MAHASISWA","",contentValues);
    }

    public void delete_mahasiswa(String nama){
        this.getWritableDatabase().delete("MAHASISWA","NAMA='"+nama+"'",null);
    }

    public void update_mahasiswa(String old_nama, String new_nama){
        this.getWritableDatabase().execSQL("UPDATE MAHASISWA SET NAMA='"+new_nama+"' WHERE NAMA='"+old_nama+"'");
    }

    public void list_all_mahasiswa(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MAHASISWA", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }


}

