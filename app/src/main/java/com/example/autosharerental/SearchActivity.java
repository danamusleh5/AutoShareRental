package com.example.autosharerental;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity {

    private EditText locationEditText;
    private TextView chooseFromTextView, chooseToTextView, textErrorTextView;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize views
        locationEditText = findViewById(R.id.location);
        chooseFromTextView = findViewById(R.id.chooseFrom);
        chooseToTextView = findViewById(R.id.chooseTo);
        textErrorTextView = findViewById(R.id.textError);
        searchButton = findViewById(R.id.searchButton);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        chooseFromTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        chooseFromTextView.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        chooseToTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        chooseToTextView.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform search action
                performSearch();
            }
        });
    }

    private void performSearch() {
        // Get input values
        String location = locationEditText.getText().toString();
        String pickUpDate = chooseFromTextView.getText().toString();
        String dropOffDate = chooseToTextView.getText().toString();

        if (location.isEmpty() || pickUpDate.isEmpty() || dropOffDate.isEmpty()) {
            textErrorTextView.setText("Please fill in all fields.");
        } else {
            textErrorTextView.setText("");

            // Perform search operation using the input values
            // You can implement your search logic here
            // For example, you can start a new activity to display search results
            // Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
            // intent.putExtra("location", location);
            // intent.putExtra("pickUpDate", pickUpDate);
            // intent.putExtra("dropOffDate", dropOffDate);
            // startActivity(intent);
        }
    }
}
