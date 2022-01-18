package com.example.squadapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.Publisher;

import java.util.List;

@Dao
public interface ArtistDao {

    @Query("SELECT * FROM artist")
    List<Artist> getAll();

    @Query("SELECT nama_artist FROM artist")
    List<String> getAllArtist();

    @Query("INSERT INTO artist (nama_artist) VALUES(:nama_artist)")
    void insertAll(String nama_artist);

    @Query("UPDATE artist SET nama_artist=:nama_artist WHERE id_artist=:id_artist")
    void update(int id_artist, String nama_artist);

    @Query("SELECT * FROM artist WHERE id_artist=:id_artist")
    Artist get(int id_artist);

    @Query("SELECT id_artist FROM artist WHERE nama_artist=:nama_artist")
    Artist getArtist(String nama_artist);

    @Delete
    void delete(Artist artist);
}
