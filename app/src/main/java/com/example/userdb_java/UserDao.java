package com.example.userdb_java;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(UserEntity userEntity);

    @Update
    void updateUser(UserEntity userEntity);

    @Delete
    void deleteUser(UserEntity userEntity);

    @Query("SELECT * FROM TABLE_NAME")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("SELECT * FROM TABLE_NAME WHERE id LIKE :id")
    UserEntity getUser(int id);

    @Query("DELETE FROM TABLE_NAME WHERE id IN (:userIds)")
    void deleteUsersByIds(List<Integer> userIds);
}