package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Lagu;
import com.example.squadapp.database.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

public class LaguActivity extends AppCompatActivity {
    private EditText editLagu;
    private Spinner spinner_genre;
    private Button btnSave;
    private AppDatabase database;
    private List<String> listGenre = new ArrayList<>();
    private int id_lagu = 0;
    private boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lagu);

        editLagu = findViewById(R.id.nama_lagu);
        btnSave = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());

        listGenre.clear();
        listGenre.addAll(database.genreDao().getAllGenre());

        Intent intent = getIntent();
        id_lagu = intent.getIntExtra("id_lagu", 0);
        if (id_lagu>0){
            isEdit = true;
            Lagu lagu = database.laguDao().get(id_lagu);
            editLagu.setText(lagu.nama_lagu);
        }else{
            isEdit = false;
        }

        spinner_genre = findViewById(R.id.spiner_genre);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGenre);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_genre.setAdapter(spinnerAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.laguDao().update(id_lagu, editLagu.getText().toString());
                }else{
                    database.laguDao().insertAll(editLagu.getText().toString());
                }
                finish();
            }
        });


    }
}