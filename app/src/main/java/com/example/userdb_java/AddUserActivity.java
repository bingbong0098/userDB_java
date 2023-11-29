package com.example.userdb_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.userdb_java.databinding.ActivityAddUserBinding;

public class AddUserActivity extends AppCompatActivity {

    private UserDatabase db;

    UserDao userDao;

    UserEntity userEntity;

    private ActivityAddUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userEntity = new UserEntity();

        db = Room.databaseBuilder(
                        this, UserDatabase.class, "NOTE_DATABASE")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        userDao = db.userDao();

        binding.saveBtn.setOnClickListener(view -> {
            userEntity.setName(binding.nameEdt.getText().toString());
            userEntity.setFamily(binding.familyEdt.getText().toString());
            userEntity.setAge(binding.ageEdt.getText().toString());
            userEntity.setNationalNumber(binding.nationalNumberEdt.getText().toString());
            userDao.addUser(userEntity);

            startActivity(new Intent(AddUserActivity.this, MainActivity.class));
            finish();
        });
    }
}