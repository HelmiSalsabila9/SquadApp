package com.example.squadapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.squadapp.database.entity.Lagu;
import com.example.squadapp.database.model.Relasi;
import com.example.squadapp.database.model.RelasiDua;
import com.example.squadapp.database.model.RelasiTIga;

import java.util.Collection;
import java.util.List;

@Dao
public interface LaguDao {

    @Query("SELECT * FROM lagu")
    List<Lagu> getAll();

    @Query("INSERT INTO lagu (nama_lagu) VALUES(:nama_lagu)")
    void insertAll(String nama_lagu);

    @Query("UPDATE lagu SET nama_lagu=:nama_lagu WHERE id_lagu=:id_lagu")
    void update(int id_lagu, String nama_lagu);

    @Query("SELECT * FROM lagu WHERE id_lagu=:id_lagu")
    Lagu get(int id_lagu);

    @Transaction
    @Query("SELECT * FROM lagu")
    public List<RelasiDua> getLaguWithGenre();

    @Transaction
    @Query("SELECT * FROM lagu")
    public List<Relasi> getLaguWithArtist();

    @Transaction
    @Query("SELECT * FROM lagu")
    public List<RelasiTIga> getLaguWithPublisher();

    @Query("INSERT INTO lagu (nama_lagu,artistId,genreId,publisherId) VALUES(:nama_lagu, :artistId, :genreId, :publisherId)")
    void insertAll(String nama_lagu, String artistId, String genreId, String publisherId);

    @Query("UPDATE lagu SET nama_lagu=:nama_lagu, artistId=:artistId, genreId=:genreId, publisherId=:publisherId WHERE id_lagu=:id_lagu")
    void update(int id_lagu, String nama_lagu, String artistId, String genreId,  String publisherId);

    @Delete
    void delete(Lagu lagu);
}
