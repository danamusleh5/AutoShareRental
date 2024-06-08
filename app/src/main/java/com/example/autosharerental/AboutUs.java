package com.example.autosharerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class AboutUs extends AppCompatActivity {


    private ImageView imageView1, imageView2, imageView3;
    private Button btn_contactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getReferance();

        btn_contactUs = findViewById(R.id.btn_contactUs);

//        btn_contactUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), ContactUs.class);
//                startActivity(i);
//            }
//        });
    }


    private void getReferance() {


    }
}