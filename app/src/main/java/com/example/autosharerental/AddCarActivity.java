package com.example.autosharerental;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddCarActivity extends AppCompatActivity {

    EditText editTextCarName, editTextCarModel, editTextCarYear, editTextCarPrice;
    Button buttonAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);

        // Initialize views
        editTextCarName = findViewById(R.id.editTextCarName);
        editTextCarModel = findViewById(R.id.editTextCarModel);
        editTextCarYear = findViewById(R.id.editTextCarYear);
        editTextCarPrice = findViewById(R.id.editTextCarPrice);
        buttonAddCar = findViewById(R.id.buttonAddCar);

        // Add Car button click listener
        buttonAddCar.setOnClickListener(view -> addCar());
    }

    private void addCar() {
        // Get car details from EditText fields
        String carName = editTextCarName.getText().toString().trim();
        String carModel = editTextCarModel.getText().toString().trim();
        String carYear = editTextCarYear.getText().toString().trim();
        String carPrice = editTextCarPrice.getText().toString().trim();


    }
}

