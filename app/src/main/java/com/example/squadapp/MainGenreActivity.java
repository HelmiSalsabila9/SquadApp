package com.example.squadapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.squadapp.adapter.ArtistAdapter;
import com.example.squadapp.adapter.GenreAdapter;
import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class MainGenreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private GenreAdapter genreAdapter;
    private List<Genre> list = new ArrayList<>();
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_genre);

        recyclerView = findViewById(R.id.genre_view);
        btnTambah = findViewById(R.id.btn_tambah);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.genreDao().getAll());
        genreAdapter = new GenreAdapter(getApplicationContext(), list);
        genreAdapter.setDialog(new GenreAdapter.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainGenreActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainGenreActivity.this,
                                        GenreActivity.class);
                                intent.putExtra("id_genre", list.get(position).id_genre);
                                startActivity(intent);
                                break;
                            case 1:
                                Genre genre = list.get(position);
                                database.genreDao().delete(genre);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(genreAdapter);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainGenreActivity.this, GenreActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.genreDao().getAll());
        genreAdapter.notifyDataSetChanged();
    }
}