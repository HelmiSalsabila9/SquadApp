package com.example.squadapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.squadapp.database.entity.Artist;
import com.example.squadapp.database.entity.Genre;

import java.util.Collection;
import java.util.List;

@Dao
public interface GenreDao {
    @Query("SELECT * FROM genre")
    List<Genre> getAll();

    @Query("SELECT nama_genre FROM genre")
    List<String> getAllGenres();

    @Query("INSERT INTO genre (nama_genre) VALUES(:nama_genre)")
    void insertAll(String nama_genre);

    @Query("UPDATE genre SET nama_genre=:nama_genre WHERE id_genre=:id_genre")
    void update(int id_genre, String nama_genre);

    @Query("SELECT * FROM genre WHERE id_genre=:id_genre")
    Genre get(int id_genre);

    @Query("SELECT id_genre FROM genre WHERE nama_genre=:nama_genre")
    Genre getGenre(String nama_genre);

    @Delete
    void delete(Genre genre);

    default Collection<String> getAllGenre() {
        return null;
    }
}
