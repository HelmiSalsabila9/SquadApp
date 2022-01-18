package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView textView_nama,textView_email,textView_alamat,textView_telepon,textView_jk,textView_hobi;
    Button button_back;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAMA = "namalengkap";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_TELEPON = "telepon";
    private static final String KEY_JK = "jk";
    private static final String KEY_HOBI = "hobi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FullScreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        textView_nama = findViewById(R.id.det_nama);
        textView_email = findViewById(R.id.det_email);
        textView_alamat = findViewById(R.id.det_alamat);
        textView_telepon = findViewById(R.id.det_telepon);
        textView_jk = findViewById(R.id.det_jk);
        textView_hobi = findViewById(R.id.det_hobi);
        button_back = findViewById(R.id.btn_det_back);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String nama = sharedPreferences.getString(KEY_NAMA,null);
        String email = sharedPreferences.getString(KEY_EMAIL,null);
        String alamat = sharedPreferences.getString(KEY_ALAMAT,null);
        String telepon = sharedPreferences.getString(KEY_TELEPON,null);
        String jk = sharedPreferences.getString(KEY_JK,null);
        String hobi = sharedPreferences.getString(KEY_HOBI,null);

        if(nama != null || email != null || alamat != null || telepon != null){
            //so set data on textview
            textView_nama.setText(nama);
            textView_email.setText(email);
            textView_alamat.setText(alamat);
            textView_telepon.setText(telepon);
            textView_jk.setText(jk);
            textView_hobi.setText(hobi);
        }

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,BerandaActivity.class);
                startActivity(intent);
            }
        });
    }
}