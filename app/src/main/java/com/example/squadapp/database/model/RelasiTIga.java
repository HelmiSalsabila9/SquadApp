package com.example.squadapp.database.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.squadapp.database.entity.Lagu;
import com.example.squadapp.database.entity.Publisher;

@Entity
public class RelasiTIga {
    @Embedded
    public Lagu lagu;

    @Relation(

            parentColumn = "publisherId",
            entityColumn = "id_publisher"
    )
    public Publisher publisher;
}
