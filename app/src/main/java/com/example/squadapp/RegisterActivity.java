package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, namalengkap, email, password, alamat, telepon, jk, hobi;
    Button buttonRegister;
    TextView buttonLogin;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAMALENGKAP = "namalengkap";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_JK = "jk";
    private static final String KEY_TELEPON = "telepon";
    private static final String KEY_HOBI = "hobi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FullScreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.tx_username);
        namalengkap = findViewById(R.id.tx_namalengkap);
        email    = findViewById(R.id.tx_email);
        password = findViewById(R.id.tx_password);
        alamat   = findViewById(R.id.tx_alamat);
        jk   = findViewById(R.id.tx_jk);
        telepon   = findViewById(R.id.tx_telepon);
        hobi   = findViewById(R.id.tx_hobi);

        buttonRegister = findViewById(R.id.button_regis);
        buttonLogin = findViewById(R.id.tx_masuk);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_USERNAME,null);

        if (name != null){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USERNAME,username.getText().toString());
                editor.putString(KEY_NAMALENGKAP,namalengkap.getText().toString());
                editor.putString(KEY_EMAIL,email.getText().toString());
                editor.putString(KEY_PASSWORD,password.getText().toString());
                editor.putString(KEY_ALAMAT,alamat.getText().toString());
                editor.putString(KEY_TELEPON,telepon.getText().toString());
                editor.putString(KEY_JK,jk.getText().toString());
                editor.putString(KEY_HOBI,hobi.getText().toString());
                editor.apply();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

                Toast.makeText(RegisterActivity.this,"Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void masuk(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}