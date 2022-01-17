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
import com.example.squadapp.adapter.UserAdapter;
import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MainArtistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private ArtistAdapter artistAdapter;
    private List<Artist> list = new ArrayList<>();
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_artist);

        recyclerView = findViewById(R.id.artist_view);
        btnTambah = findViewById(R.id.btn_tambah);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.artistDao().getAll());
        artistAdapter = new ArtistAdapter(getApplicationContext(), list);
        artistAdapter.setDialog(new ArtistAdapter.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainArtistActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainArtistActivity.this,
                                        ArtistActivity.class);
                                intent.putExtra("id_artist", list.get(position).id_artist);
                                startActivity(intent);
                                break;
                            case 1:
                                Artist artist = list.get(position);
                                database.artistDao().delete(artist);
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
        recyclerView.setAdapter(artistAdapter);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainArtistActivity.this, ArtistActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.artistDao().getAll());
        artistAdapter.notifyDataSetChanged();
    }
}