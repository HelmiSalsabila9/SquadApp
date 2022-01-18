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
import com.example.squadapp.adapter.LaguAdapter;
import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.Lagu;
import com.example.squadapp.database.model.Relasi;
import com.example.squadapp.database.model.RelasiDua;
import com.example.squadapp.database.model.RelasiTIga;

import java.util.ArrayList;
import java.util.List;

public class MainLaguActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private LaguAdapter laguAdapter;
    private List<Relasi> listArtist = new ArrayList<>();
    private List<RelasiDua> listGenre = new ArrayList<>();
    private List<RelasiTIga> listPublisher = new ArrayList<>();
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_lagu);

        recyclerView = findViewById(R.id.lagu_view);
        btnTambah = findViewById(R.id.btn_tambah);
        database = AppDatabase.getInstance(getApplicationContext());
        listGenre.clear();
        listGenre.addAll(database.laguDao().getLaguWithGenre());
        listPublisher.clear();
        listPublisher.addAll(database.laguDao().getLaguWithPublisher());

        listArtist.clear();
        listArtist.addAll(database.laguDao().getLaguWithArtist());
        laguAdapter = new LaguAdapter(getApplicationContext(), listArtist);
        laguAdapter.setDialog(new LaguAdapter.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainLaguActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainLaguActivity.this,
                                        LaguActivity.class);
                                intent.putExtra("id_lagu", listArtist.get(position).lagu.id_lagu);
                                intent.putExtra("id_lagu", listGenre.get(position).lagu.id_lagu);
                                intent.putExtra("id_lagu", listPublisher.get(position).lagu.id_lagu);
                                startActivity(intent);
                                break;
                            case 1:
                                Relasi laguArtist = listArtist.get(position);
                                RelasiDua laguGenre = listGenre.get(position);
                                RelasiTIga laguPublisher = listPublisher.get(position);
                                database.laguDao().delete(laguArtist.lagu);
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
        recyclerView.setAdapter(laguAdapter);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainLaguActivity.this, LaguActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        listArtist.clear();
        listArtist.addAll(database.laguDao().getLaguWithArtist());
        listGenre.clear();
        listGenre.addAll(database.laguDao().getLaguWithGenre());
        listPublisher.clear();
        listPublisher.addAll(database.laguDao().getLaguWithPublisher());

        laguAdapter.notifyDataSetChanged();
    }



}