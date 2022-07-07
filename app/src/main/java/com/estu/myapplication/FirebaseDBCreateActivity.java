package com.estu.myapplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.estu.myapplication.model.Data;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDBCreateActivity extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button bt1;
    private EditText et1;
    private EditText et2;
    private EditText et3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_create);

        et1 = (EditText) findViewById(R.id.et_nama);
        et2 = (EditText) findViewById(R.id.et_nim);
        et3 = (EditText) findViewById(R.id.et_kelas);
        bt1 = (Button) findViewById(R.id.simpan);

        database = FirebaseDatabase.getInstance().getReference();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(et1.getText().toString()) && !isEmpty(et2.getText().toString()) && !isEmpty(et3.getText().toString()))
                    submitDatadiri(new Data(et1.getText().toString(), et2.getText().toString(), et3.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.simpan), "Data barang tidak boleh kosong!", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        et1.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    private void updateDatadiri(Data datadiri) {
    }

    private void submitDatadiri(Data datadiri) {

        database.child("datadiri").push().setValue(datadiri).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                et1.setText("");
                et2.setText("");
                et3.setText("");
                Snackbar.make(findViewById(R.id.simpan), "Data berhasil ditambahkan!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {

        return new Intent(activity, FirebaseDBCreateActivity.class);
    }
}
