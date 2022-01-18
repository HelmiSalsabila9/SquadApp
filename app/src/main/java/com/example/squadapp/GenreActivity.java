package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Genre;
import com.example.squadapp.database.entity.Lagu;

public class GenreActivity extends AppCompatActivity {
    private EditText editGenre;
    private Button btnSave;
    private AppDatabase database;
    private int id_genre = 0;
    private boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_genre);

        editGenre = findViewById(R.id.nama_genre);
        btnSave = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        id_genre = intent.getIntExtra("id_genre", 0);
        if (id_genre>0){
            isEdit = true;
            Genre genre = database.genreDao().get(id_genre);
            editGenre.setText(genre.nama_genre);
        }else{
            isEdit = false;
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.genreDao().update(id_genre, editGenre.getText().toString());
                }else{
                    database.genreDao().insertAll(editGenre.getText().toString());
                }
                finish();
            }
        });
    }
}
