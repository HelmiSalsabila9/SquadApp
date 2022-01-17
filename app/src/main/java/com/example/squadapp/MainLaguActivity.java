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

import java.util.ArrayList;
import java.util.List;

public class MainLaguActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private LaguAdapter laguAdapter;
    private List<Lagu> list = new ArrayList<>();
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_lagu);

        recyclerView = findViewById(R.id.lagu_view);
        btnTambah = findViewById(R.id.btn_tambah);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.laguDao().getAll());
        laguAdapter = new LaguAdapter(getApplicationContext(), list);
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
                                intent.putExtra("id_lagu", list.get(position).id_lagu);
                                startActivity(intent);
                                break;
                            case 1:
                                Lagu lagu = list.get(position);
                                database.laguDao().delete(lagu);
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
        list.clear();
        list.addAll(database.laguDao().getAll());
        laguAdapter.notifyDataSetChanged();
    }



}