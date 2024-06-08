package com.example.autosharerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnAddUser = findViewById(R.id.btnAddUser);
        Button btnViewUsers = findViewById(R.id.btnViewUsers);
        Button btnOpenProfile = findViewById(R.id.btnOpenProfile);
        Button btnOpenHome = findViewById(R.id.homeButton);
        ImageView addCar = findViewById(R.id.iconAddCar);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddUserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ViewAllUsers.class);
                startActivity(intent);
                finish();
            }
        });

        btnOpenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnOpenHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddCarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

