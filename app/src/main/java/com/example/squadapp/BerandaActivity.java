package com.example.squadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class BerandaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_beranda);
    }

    public void masuk(View view) {
        startActivity(new Intent(BerandaActivity.this,LoginActivity.class));
    }

    public void detail(View view) {
        startActivity(new Intent(BerandaActivity.this,DetailActivity.class));
    }

    public void dash_tambah(View view) {
        startActivity(new Intent(BerandaActivity.this,MainActivity.class));
    }

    public void dash_lagu(View view) {
        startActivity(new Intent(BerandaActivity.this,MainLaguActivity.class));
    }

    public void dash_publisher(View view) {
        startActivity(new Intent(BerandaActivity.this,MainPublisherActivity.class));
    }

    public void dash_artist(View view) {
        startActivity(new Intent(BerandaActivity.this,MainArtistActivity.class));
    }

    public void dash_genre(View view) {
        startActivity(new Intent(BerandaActivity.this,MainGenreActivity.class));
    }
}