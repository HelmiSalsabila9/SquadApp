package com.example.squadapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.squadapp.database.entity.Lagu;
import com.example.squadapp.database.entity.Publisher;

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

    @Delete
    void delete(Lagu lagu);
}
