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

import com.example.squadapp.adapter.LaguAdapter;
import com.example.squadapp.adapter.PublisherAdapter;
import com.example.squadapp.database.AppDatabase;
import com.example.squadapp.database.entity.Lagu;
import com.example.squadapp.database.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

public class MainPublisherActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private PublisherAdapter publisherAdapter;
    private List<Publisher> list = new ArrayList<>();
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_publisher);

        recyclerView = findViewById(R.id.publisher_view);
        btnTambah = findViewById(R.id.btn_tambah);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.publisherDao().getAll());
        publisherAdapter = new PublisherAdapter(getApplicationContext(), list);
        publisherAdapter.setDialog(new PublisherAdapter.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainPublisherActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainPublisherActivity.this,
                                        PublisherActivity.class);
                                intent.putExtra("id_publisher", list.get(position).id_publisher);
                                startActivity(intent);
                                break;
                            case 1:
                                Publisher publisher = list.get(position);
                                database.publisherDao().delete(publisher);
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
        recyclerView.setAdapter(publisherAdapter);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPublisherActivity.this, PublisherActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.publisherDao().getAll());
        publisherAdapter.notifyDataSetChanged();
    }
}