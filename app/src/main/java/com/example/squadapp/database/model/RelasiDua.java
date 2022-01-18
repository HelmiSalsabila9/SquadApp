package com.example.squadapp.database.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.Lagu;

@Entity
public class RelasiDua {
    @Embedded
    public Lagu lagu;

    @Relation(

            parentColumn = "artistId",
            entityColumn = "id_artist"
    )
    public Artist artist;
}
