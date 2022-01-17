package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editText_email, editText_password;
    TextView buttonRegister;
    Button buttonLogin;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FullScreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        editText_email = findViewById(R.id.tx_email);
        editText_password = findViewById(R.id.tx_password);
        buttonRegister = findViewById(R.id.tx_regis);
        buttonLogin = findViewById(R.id.button_login);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);

        if (email != null || password != null) {
            //so set data on textview
            editText_email.setText(email);
            editText_password.setText(password);
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValidate = editText_email.getText().toString();
                String passwordValidate = editText_password.getText().toString();
                if (emailValidate.equals(email) && passwordValidate.equals(password)) {
                    Toast.makeText(LoginActivity.this, "Masuk Berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
                    startActivity(intent);
                } else if (emailValidate.isEmpty() && passwordValidate.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill your email and password!", Toast.LENGTH_SHORT).show();
                } else if (emailValidate.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill your email!", Toast.LENGTH_SHORT).show();
                } else if (passwordValidate.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill your password!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Email or password wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}