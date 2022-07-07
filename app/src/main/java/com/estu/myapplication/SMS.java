package com.estu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class SMS extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText btn_nohp, btn_pesan;
    Button btn_kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        countryCodePicker = findViewById(R.id.btn_negara);
        btn_nohp = findViewById(R.id.btn_nohp);
        btn_pesan = findViewById(R.id.btn_pesan);
        btn_kirim = findViewById(R.id.btn_kirim);

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirimPesanManager();
            }
        });
    }

    private void kirimPesanManager() {
        Uri uri = Uri.parse("smsto:" + btn_nohp.getText().toString());
        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);
        smsSIntent.putExtra("sms_body", btn_pesan.getText().toString());
        try {
            startActivity(smsSIntent);
        } catch (Exception e) {
            Toast.makeText(SMS.this, "Gagal Mengirim SMS...",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}