package com.example.c5_p25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendListActivity extends AppCompatActivity {
    ListView lv;
    DBHandler db;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        db = new DBHandler(this);
        ArrayList<HashMap<String,String>> userList = db.GetAllInHash();
        lv = findViewById(R.id.list_friends);
        ListAdapter adapter = new SimpleAdapter(this, userList, R.layout.row_friend,
                new String[] { "id","firstname", "lastname","email"},
                new int[] {R.id.idFriend,R.id.idFirstName, R.id.idLastName, R.id.idEmail});
        lv.setAdapter(adapter);
        back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(FriendListActivity.this, MainActivity.class);
            startActivity(intent);
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String,String> selectedItem = (HashMap<String, String>) adapterView.getItemAtPosition(i);
                int selectedId = Integer.parseInt(selectedItem.get("id"));
                db.deleteById(selectedId);
                Intent it = new Intent(FriendListActivity.this, DeleteActivity.class);
                startActivity(it);
            }
        });
    }
}