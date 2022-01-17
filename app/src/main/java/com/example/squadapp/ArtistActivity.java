package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.Genre;

public class ArtistActivity extends AppCompatActivity {
    private EditText editArtist;
    private Button btnSave;
    private AppDatabase database;
    private int id_artist = 0;
    private boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_artist);

        editArtist = findViewById(R.id.nama_artist);
        btnSave = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        id_artist = intent.getIntExtra("id_artist", 0);
        if (id_artist>0){
            isEdit = true;
            Artist artist = database.artistDao().get(id_artist);
            editArtist.setText(artist.nama_artist);
        }else{
            isEdit = false;
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.artistDao().update(id_artist, editArtist.getText().toString());
                }else{
                    database.artistDao().insertAll(editArtist.getText().toString());
                }
                finish();
            }
        });
    }
}