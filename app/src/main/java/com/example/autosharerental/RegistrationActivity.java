package com.example.autosharerental;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    EditText firstname_txt ,lastname_txt , email_txt , number_txt , password , age , city;
    Spinner gender_spn;
    Button signUpButton , signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setupViews();
    }

    private void setupViews() {
        firstname_txt = findViewById(R.id.edttxt_fName);
        lastname_txt = findViewById(R.id.edttxt_lName);
        email_txt = findViewById(R.id.email_txt);
        password = findViewById(R.id.password);
        gender_spn = findViewById(R.id.gender_spn);
        number_txt = findViewById(R.id.edttxt_phoneN);
        age = findViewById(R.id.age);
        city = findViewById(R.id.city);
        signUpButton = findViewById(R.id.singUp_btn);
        signInButton = findViewById(R.id.signIn_btn);

        String[] genderChoice = {"Select Your Gender", "Female", "Male"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, genderChoice);
        gender_spn.setAdapter(genderAdapter);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAllChecked = checkInformation();
                if (isAllChecked) {
                storeIntoDataBase();
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email_txt = text.getText().toString();
        return (!TextUtils.isEmpty(email_txt) && Patterns.EMAIL_ADDRESS.matcher(email_txt).matches());
    }

    private boolean checkInformation() {
        if (firstname_txt.getText().toString().isEmpty()) {
            firstname_txt.setError("First Name is Required");
            return false;
        }

        if (lastname_txt.getText().toString().isEmpty()) {
            lastname_txt.setError("Last Name is Required");
            return false;
        }

        if (email_txt.getText().toString().isEmpty()) {
            email_txt.setError("Email is Required");
            return false;
        } else if (!isEmail(email_txt)) {
            email_txt.setError("Please Enter a Valid Email Address");
            return false;
        }

        if (number_txt.getText().toString().isEmpty()) {
            number_txt.setError("Phone Number is Required");
            return false;
        }

        if (password.getText().toString().isEmpty() || password.getText().toString().length() < 6) {
            password.setError("Please Enter a Password With at Least 6 Characters");
            return false;

        }
        if (age.getText().toString().isEmpty()) {
            age.setError("Please Enter Your Age");
            return false;
        }

        if (city.getText().toString().isEmpty()) {
            city.setError("Please Enter Your City");
            return false;
        }

        return true;
    }

    void storeIntoDataBase(){
    boolean isAllChecked = checkInformation();
        if (isAllChecked) {
            // Get a reference to the Firestore database
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Create a new document in the "users" collection
            String firstName = firstname_txt.getText().toString();
            String lastName = lastname_txt.getText().toString();
            String email = email_txt.getText().toString();
            String gender = gender_spn.getSelectedItem().toString();
            String phoneNumber = number_txt.getText().toString();
            String userAge = age.getText().toString();
            String userCity = city.getText().toString();
            String pass = password.getText().toString();

            // Create a new user object
            Map<String, Object> user = new HashMap<>();
            user.put("firstName", firstName);
            user.put("lastName", lastName);
            user.put("email", email);
            user.put("gender", gender);
            user.put("phoneNumber", phoneNumber);
            user.put("age", userAge);
            user.put("city", userCity);
            user.put("password", pass);
            user.put("role","Renter");


            // Add the user to the database
            Task<DocumentReference> users = db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            // Handle successful addition of user
                            Toast.makeText(RegistrationActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                            // Clear input fields after successful addition
                            clearInputFields();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failure to add user
                            Toast.makeText(RegistrationActivity.this, "Failed to add user: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                   }
                 }
                private void clearInputFields() {
                    // Clear the text in all EditText fields
                    firstname_txt.setText("");
                    lastname_txt.setText("");
                    email_txt.setText("");
                    number_txt.setText("");
                    password.setText("");
                    age.setText("");
                    city.setText("");

                    gender_spn.setSelection(0);
                }

}
