package com.estu.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sqlite extends AppCompatActivity {

    EditText nama,nim;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        nama = (EditText)findViewById(R.id.et_nama);
        nim = (EditText)findViewById(R.id.et_nim);
        textView = (TextView)findViewById(R.id.textView);

        controller = new DB_Controller(this,"",null,1);
    }

    public void btn_click(View view){
        switch(view.getId()){
            case R.id.btn_simpan:
                try {
                    controller.insert_mahasiswa(nama.getText().toString(),nim.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(Sqlite.this,"ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_hapus:
                controller.delete_mahasiswa(nama.getText().toString());
                break;
            case R.id.btn_edit:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Sqlite.this);
                dialog.setTitle("ENTER NEW FIRSTNAME ");

                final EditText new_nama = new EditText(this);
                dialog.setView(new_nama);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        controller.update_mahasiswa(nama.getText().toString(),new_nama.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.btn_tampil:
                controller.list_all_mahasiswa(textView);
                break;
        }

    }
}