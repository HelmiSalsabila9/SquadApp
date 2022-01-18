package com.example.squadapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.squadapp.database.entity.Publisher;

import java.util.List;

@Dao
public interface PublisherDao {

    @Query("SELECT * FROM publisher")
    List<Publisher> getAll();

    @Query("SELECT nama_publisher FROM publisher")
    List<String> getAllPublisher();

    @Query("INSERT INTO publisher (nama_publisher) VALUES(:nama_publisher)")
    void insertAll(String nama_publisher);

    @Query("UPDATE publisher SET nama_publisher=:nama_publisher WHERE id_publisher=:id_publisher")
    void update(int id_publisher, String nama_publisher);

    @Query("SELECT * FROM publisher WHERE id_publisher=:id_publisher")
    Publisher get(int id_publisher);

    @Query("SELECT id_publisher FROM publisher WHERE nama_publisher=:nama_publisher")
    Publisher getPublisher(String nama_publisher);

    @Delete
    void delete(Publisher publisher);
}
