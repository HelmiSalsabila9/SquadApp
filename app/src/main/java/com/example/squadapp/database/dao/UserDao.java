package com.example.squadapp.database.dao;

import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Delete;

import com.example.squadapp.database.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();
    @Query("INSERT INTO user (name,email) VALUES(:name, :email)")
    void insertAll(String name, String email);
    @Query("UPDATE user SET name=:name, email=:email WHERE uid=:uid")
    void update(int uid, String name, String email);
    @Query("SELECT * FROM user WHERE uid=:uid")
    User get(int uid);
    @Delete
    void delete(User user);

}
