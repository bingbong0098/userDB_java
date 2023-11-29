package com.example.userdb_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.userdb_java.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    private UserDatabase db;
    UserDao userDao;

    UserEntity userEntity;

    private ActivityUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userEntity = new UserEntity();

        db = Room.databaseBuilder(
                        this, UserDatabase.class, "NOTE_DATABASE")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        userDao = db.userDao();


        int userId = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("bundle_user_id", 0);
        }

        final int finalUserId = userId;
        binding.nameEdt.setText(userDao.getUser(userId).getName());
        binding.familyEdt.setText(userDao.getUser(userId).getFamily());
        binding.ageEdt.setText(userDao.getUser(userId).getAge());
        binding.nationalNumberEdt.setText(userDao.getUser(userId).getNationalNumber());

        binding.updateBtn.setOnClickListener(view -> {
            userEntity.setId(finalUserId);
            userEntity.setName(binding.nameEdt.getText().toString());
            userEntity.setFamily(binding.familyEdt.getText().toString());
            userEntity.setAge(binding.ageEdt.getText().toString());
            userEntity.setNationalNumber(binding.nationalNumberEdt.getText().toString());

            userDao.updateUser(userEntity);
            startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            finish();
        });

        binding.deleteBtn.setOnClickListener(view -> {
            userEntity.setId(finalUserId);
            userDao.deleteUser(userEntity);
            startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            finish();
        });
    }
}