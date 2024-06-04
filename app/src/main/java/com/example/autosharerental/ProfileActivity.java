package com.example.autosharerental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileActivity extends AppCompatActivity {

    TextView firstName, lastName, emailText, phoneNumber, gender, birthDate , roleText;
    Button logout, updateProfile;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailText = findViewById(R.id.emailtxt);
        phoneNumber = findViewById(R.id.phoneNumber);
        gender = findViewById(R.id.gender);
        birthDate = findViewById(R.id.birthDate);
        roleText = findViewById(R.id.role);

        logout = findViewById(R.id.logout);
        updateProfile = findViewById(R.id.updateProfile);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userEmail = getUserEmail();

        if (userEmail != null) {
            fetchUserDataFromDatabase(userEmail);
        } else {

            Toast.makeText(this, "User email not found", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        });

        updateProfile.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, UpdateProfile.class);
            startActivity(intent);
            finish();
        });
    }

    private String getUserEmail() {

        return sharedPreferences.getString("user_email", null);
    }

    void fetchUserDataFromDatabase(String email) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            DocumentSnapshot document = querySnapshot.getDocuments().get(0);

                            firstName.setText(document.getString("firstName"));
                            lastName.setText(document.getString("lastName"));
                            emailText.setText(document.getString("email"));
                            phoneNumber.setText(document.getString("phoneNumber"));
                            gender.setText(document.getString("gender"));
                            birthDate.setText(document.getString("birthDate"));
                            roleText.setText(document.getString("role"));
                        } else {
                            Toast.makeText(ProfileActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProfileActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
