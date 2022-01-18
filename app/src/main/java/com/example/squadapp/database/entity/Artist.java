package com.example.squadapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Artist {
    @PrimaryKey
    public int id_artist;
    @ColumnInfo(name = "nama_artist")
    public String nama_artist;
}
