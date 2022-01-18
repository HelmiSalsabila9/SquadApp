package com.example.squadapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Publisher {

    @PrimaryKey
    public int id_publisher;
    @ColumnInfo(name = "nama_publisher")
    public String nama_publisher;

}
