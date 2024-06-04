package com.example.autosharerental;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword,
            editTextPhoneNumber, editTextCity, editTextAge;
    Spinner genderSpinner;
    Button buttonAddUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lessoruser);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextCity = findViewById(R.id.editTextCity);
        editTextAge = findViewById(R.id.editTextAge);
        genderSpinner = findViewById(R.id.gender_spn);
        buttonAddUser = findViewById(R.id.buttonAddUser);

        String[] genderChoice = {"Select Your Gender", "Female", "Male"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, genderChoice);
        genderSpinner.setAdapter(genderAdapter);

        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAllChecked = checkInformation();
                if (isAllChecked) {
                    storeIntoDataBase();
                }
            }
        });
    }


    private boolean checkInformation() {
        if (editTextFirstName.getText().toString().isEmpty()) {
            editTextFirstName.setError("First Name is Required");
            return false;
        }

        if (editTextLastName.getText().toString().isEmpty()) {
            editTextLastName.setError("Last Name is Required");
            return false;
        }

        if (editTextEmail.getText().toString().isEmpty()) {
            editTextEmail.setError("Email is Required");
            return false;
        } else if (!isEmail(editTextEmail)) {
            editTextEmail.setError("Please Enter a Valid Email Address");
            return false;
        }

        if (editTextPhoneNumber.getText().toString().isEmpty()) {
            editTextPhoneNumber.setError("Phone Number is Required");
            return false;
        }

        if (editTextPassword.getText().toString().isEmpty() || editTextPassword.getText().toString().length() < 6) {
            editTextPassword.setError("Please Enter a Password With at Least 6 Characters");
            return false;

        }
        if (editTextAge.getText().toString().isEmpty()) {
            editTextAge.setError("Please Enter Your Age");
            return false;
        }

        if (editTextCity.getText().toString().isEmpty()) {
            editTextCity.setError("Please Enter Your City");
            return false;
        }

        return true;
    }
    boolean isEmail(EditText text) {
        CharSequence email_txt = text.getText().toString();
        return (!TextUtils.isEmpty(email_txt) && Patterns.EMAIL_ADDRESS.matcher(email_txt).matches());
    }
    void storeIntoDataBase(){
        boolean isAllChecked = checkInformation();
        if (isAllChecked) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String email = editTextEmail.getText().toString();
            String gender = genderSpinner.getSelectedItem().toString();
            String phoneNumber = editTextPhoneNumber.getText().toString();
            String userAge = editTextAge.getText().toString();
            String userCity = editTextCity.getText().toString();
            String pass = editTextPassword.getText().toString();

            Map<String, Object> user = new HashMap<>();
            user.put("firstName", firstName);
            user.put("lastName", lastName);
            user.put("email", email);
            user.put("gender", gender);
            user.put("phoneNumber", phoneNumber);
            user.put("age", userAge);
            user.put("city", userCity);
            user.put("password", pass);
            user.put("role","Lessor");


            Task<DocumentReference> users = db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddUserActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                            clearInputFields();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failure to add user
                            Toast.makeText(AddUserActivity.this, "Failed to add user: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private void clearInputFields() {
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextEmail.setText("");
        editTextPhoneNumber.setText("");
        editTextPassword.setText("");
        editTextAge.setText("");
        editTextCity.setText("");

        genderSpinner.setSelection(0);
    }


}
