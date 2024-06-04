package com.example.autosharerental;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.autosharerental.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ViewAllUsers extends AppCompatActivity {

    ListView listViewUsers;
    List<User> userList;
    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        listViewUsers = findViewById(R.id.listViewUsers);
        userList = new ArrayList<>();
        fetchUserData();

        adapter = new UserListAdapter(this, R.layout.list_item_user, userList);

        listViewUsers.setAdapter(adapter);
    }

    void fetchUserData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    String role = document.getString("role");
                                    String name = document.getString("firstName") + " " + document.getString("lastName");
                                    String email = document.getString("email");


                                    userList.add(new User(role + ":  ", name, email));
                                } catch (Exception e) {
                                    Log.e(TAG, "Error parsing document: " + document.getId(), e);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Failed to fetch user data: " + e.getMessage(), e);
                    }
                });
    }
}


