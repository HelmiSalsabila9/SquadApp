package com.example.squadapp.database.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.squadapp.database.entity.Genre;
import com.example.squadapp.database.entity.Lagu;

@Entity
public class Relasi {
    @Embedded
    public Lagu lagu;

    @Relation(

            parentColumn = "genreId",
            entityColumn = "id_genre"
    )
    public Genre genre;

}
