package com.example.squadapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Genre {

    @PrimaryKey
    public int id_genre;
    @ColumnInfo(name = "nama_genre")
    public String nama_genre;
}
