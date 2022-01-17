package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.User;

public class TambahActivity extends AppCompatActivity {
    private EditText editName, editEmail;
    private Button btnSave;
    private AppDatabase database;
    private int uid = 0;
    private boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tambah);

        editName = findViewById(R.id.full_name);
        editEmail = findViewById(R.id.email);
        btnSave = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        if (uid>0){
            isEdit = true;
            User user = database.userDao().get(uid);
            editEmail.setText(user.email);
            editName.setText(user.fullName);
        }else{
            isEdit = false;
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.userDao().update(uid, editName.getText().toString(),
                            editEmail.getText().toString());
                }else{
                    database.userDao().insertAll(editName.getText().toString(),
                            editEmail.getText().toString());
                }
                finish();
            }
        });
    }
}
