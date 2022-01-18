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
import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.Genre;
import com.example.squadapp.database.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

public class LaguActivity extends AppCompatActivity {
    private EditText editLagu;
    private Spinner spinner_artist, spinner_genre, spinner_publisher;
    private Button btnSave;
    private AppDatabase database;
    private List<String> listArtist = new ArrayList<>();
    private List<String> listGenre = new ArrayList<>();
    private List<String> listPublisher = new ArrayList<>();
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

        listArtist.clear();
        listArtist.addAll(database.artistDao().getAllArtist());

        spinner_artist = findViewById(R.id.artistId);
        ArrayAdapter<String> spinnerAdapterArtist = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listArtist);
        spinnerAdapterArtist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_artist.setAdapter(spinnerAdapterArtist);

        listGenre.clear();
        listGenre.addAll(database.genreDao().getAllGenres());

        spinner_genre = findViewById(R.id.genreId);
        ArrayAdapter<String> spinnerAdapterGenre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGenre);
        spinnerAdapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_genre.setAdapter(spinnerAdapterGenre);

        listPublisher.clear();
        listPublisher.addAll(database.publisherDao().getAllPublisher());

        spinner_publisher = findViewById(R.id.publisherId);
        ArrayAdapter<String> spinnerAdapterPublisher = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPublisher);
        spinnerAdapterPublisher.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_publisher.setAdapter(spinnerAdapterPublisher);

        Intent intent = getIntent();
        id_lagu = intent.getIntExtra("id_lagu", 0);
        if (id_lagu>0){
            isEdit = true;
            Lagu lagu = database.laguDao().get(id_lagu);
            editLagu.setText(lagu.nama_lagu);
            Artist nama_artist = database.artistDao().getArtist(lagu.artistId);
            spinner_artist.setSelection(nama_artist.id_artist - 1);
            Genre nama_genre = database.genreDao().getGenre(lagu.genreId);
            spinner_genre.setSelection(nama_genre.id_genre - 1);
            Publisher nama_publisher = database.publisherDao().getPublisher(lagu.publisherId);
            spinner_publisher.setSelection(nama_publisher.id_publisher - 1);
        }else{
            isEdit = false;
        }



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.laguDao().update(id_lagu, editLagu.getText().toString(), spinner_artist.getSelectedItem().toString().trim()
                    , spinner_genre.getSelectedItem().toString().trim(), spinner_publisher.getSelectedItem().toString().trim());
                }else{
                    database.laguDao().insertAll(editLagu.getText().toString(), spinner_artist.getSelectedItem().toString().trim()
                            , spinner_genre.getSelectedItem().toString().trim(), spinner_publisher.getSelectedItem().toString().trim());
                }
                finish();
            }
        });


    }
}