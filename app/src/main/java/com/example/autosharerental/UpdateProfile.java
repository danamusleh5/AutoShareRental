package com.example.autosharerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateProfile extends AppCompatActivity {
     EditText fName, lName, password_edt, phoneN;
     Button save, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        fName = findViewById(R.id.fName_edt);
        lName = findViewById(R.id.lName_edt);
        password_edt = findViewById(R.id.password_edt);
        phoneN = findViewById(R.id.phoneN_edt);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedFirstName = fName.getText().toString();
                String updatedLastName = lName.getText().toString();
                String updatedPassword = password_edt.getText().toString();
                String updatedPhoneNumber = phoneN.getText().toString();

                // Update the user information in the database
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String userEmail = getIntent().getStringExtra("userEmail");
                DocumentReference userRef = db.collection("users").document(userEmail);


                userRef.update("firstName", updatedFirstName,
                                "lastName", updatedLastName,
                                "phoneNumber", updatedPhoneNumber,"password", updatedPassword)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateProfile.this, "User information updated successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateProfile.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateProfile.this, "Failed to update user information: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


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