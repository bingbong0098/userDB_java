package com.example.userdb_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.userdb_java.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.UserClickListiner {


    UsersAdapter usersAdapter;

    UserDatabase db;

    UserEntity userEntity;

    UserDao userDao;

    private List<Integer> deleteList;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        deleteList = new ArrayList<Integer>();

        db = Room.databaseBuilder(
                        this, UserDatabase.class, "NOTE_DATABASE")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        userDao = db.userDao();


        usersAdapter = new UsersAdapter(MainActivity.this);

        binding.usersRv.setLayoutManager(new LinearLayoutManager(this));
        binding.usersRv.setAdapter(usersAdapter);
        usersAdapter.setListener(MainActivity.this);

        userDao.getAllUsers().observe(this, userList -> usersAdapter.submitList(userList));

        binding.addBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddUserActivity.class));
            finish();
        });

        binding.deleteBtn.setOnClickListener(view -> {
            userDao.deleteUsersByIds(deleteList);
        });
    }

    @Override
    public void users(List<Integer> users) {
        deleteList = users;
        doSomethingWithDataList();
    }

    private void doSomethingWithDataList() {
        if (deleteList != null) {
            for (Integer data : deleteList) {
                Log.d("YourActivity", "Data: " + data);
            }
        }
    }
}