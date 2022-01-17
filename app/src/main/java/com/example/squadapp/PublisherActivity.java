package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Publisher;
import com.example.squadapp.database.entity.User;


public class PublisherActivity extends AppCompatActivity {
    private EditText editPublisher;
    private Button btnSave;
    private AppDatabase database;
    private int id_publisher = 0;
    private boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_publisher);

        editPublisher = findViewById(R.id.nama_publisher);
        btnSave = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        id_publisher = intent.getIntExtra("id_publisher", 0);
        if (id_publisher>0){
            isEdit = true;
            Publisher publisher = database.publisherDao().get(id_publisher);
            editPublisher.setText(publisher.nama_publisher);
        }else{
            isEdit = false;
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.publisherDao().update(id_publisher, editPublisher.getText().toString());
                }else{
                    database.publisherDao().insertAll(editPublisher.getText().toString());
                }
                finish();
            }
        });
    }
}