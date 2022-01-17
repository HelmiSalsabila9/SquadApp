package com.example.squadapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "name")
    public String fullName;
    public String email;

}
