package com.example.c5_p25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText emailAddress;
    Button addFriends;
    Button goFriends;
    DBHandler dbManager;
    AutoCompleteTextView searchFriend;
    Button searchFriendButton;
    TextView emailSearchResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBHandler( this );
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        emailAddress = findViewById(R.id.email);
        addFriends = findViewById(R.id.addFriends);
        goFriends = findViewById(R.id.goFriends);
        searchFriend = findViewById(R.id.searchFriend);
        searchFriendButton = findViewById(R.id.searchFriendButton);
        emailSearchResult = findViewById(R.id.searchResult);
        addFriends.setOnClickListener(view -> {
            String fn = firstName.getText().toString();
            String ln = lastName.getText().toString();
            String ea = emailAddress.getText().toString();
            Friend friend = new Friend (0, fn, ln , ea);
            dbManager.insert(friend);
            Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_SHORT).show();
            firstName.setText("");
            lastName.setText("");
            emailAddress.setText("");
        });
        goFriends.setOnClickListener(view -> {
            Intent intent = new Intent(this,FriendListActivity.class);
            startActivity(intent);
        });

        ArrayAdapter<String> ad = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, dbManager.GetAllEmails());
        searchFriend.setAdapter(ad);

        searchFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curr = searchFriend.getText().toString();
                if (curr != null){
                    String searchResult = dbManager.searchByEmail(curr);
                    if (searchResult != ""){
                        emailSearchResult.setText(searchResult);
                    }
                }
            }
        });
    }
}