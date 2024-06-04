package com.example.autosharerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class UpdateProfile extends AppCompatActivity {
     EditText fName, lName, email_edt, phoneN;
     Button save, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        fName = findViewById(R.id.fName_edt);
        lName = findViewById(R.id.lName_edt);
        email_edt = findViewById(R.id.email_edt);
        phoneN = findViewById(R.id.phoneN_edt);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfile.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfile.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

}