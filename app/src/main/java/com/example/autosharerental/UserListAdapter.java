package com.example.autosharerental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {

    private Context mContext;
    private int mResource;

    public UserListAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        User user = getItem(position);
        if (user != null) {
            TextView textViewName = convertView.findViewById(R.id.textViewName);
            TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
            TextView textViewRole = convertView.findViewById(R.id.textViewRole);

            textViewName.setText(String.format("%s%s", user.getFirstName(), user.getLastName()));
            textViewRole.setText(user.getRole());
            textViewEmail.setText(user.getEmail());

        }

        return convertView;
    }
}
