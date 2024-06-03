package com.example.autosharerental;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    ImageView welcomeImg;
    TextView welcomeTxt, welcomeText;
    Button signInButton, signUpButton , aboutUsButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Initialize views
        welcomeImg = findViewById(R.id.welcome_img);
        welcomeTxt = findViewById(R.id.welcome_txt);
        welcomeText = findViewById(R.id.welcome_text);
        signInButton = findViewById(R.id.welcome_signin);
        signUpButton = findViewById(R.id.welcome_signup);
        aboutUsButton = findViewById(R.id.welcome_about);

        // Set click listeners
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  startActivity(new Intent(WelcomeActivity.this, LogInActivity.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
            }
        });
        //// about Us
//        aboutUsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(WelcomeActivity.this, AboutUs.class));
//            }
//        });
    }
}
