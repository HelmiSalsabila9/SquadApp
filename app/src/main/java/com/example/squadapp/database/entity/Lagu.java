package com.example.squadapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Lagu {
    @PrimaryKey
    public int id_lagu;
    @ColumnInfo(name = "nama_lagu")
    public String nama_lagu;
    public String artistId;
    public String genreId;
    public String publisherId;
}
