package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {

    ListView listView;
    MyDatabaseHelper myDatabaseHelper;
    Button addNewRestaurant,addNewMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        myDatabaseHelper = new MyDatabaseHelper(this);

        listView = findViewById(R.id.listViewIdAdmin);
        addNewRestaurant =(Button) findViewById(R.id.insertRestaurant);
        addNewMenu =(Button) findViewById(R.id.insertMenu);

        addNewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this,AddNewRestaurant.class);
                startActivity(intent);
            }
        });

        addNewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this,AddNewMenu.class);
                startActivity(intent);
            }
        });

        loadData();
    }

    public void loadData() {
        ArrayList<Post> arrayList = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.getAllPostsThatNotApproved();

        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No posts to show...",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                String uid = cursor.getString(0);
                String post = cursor.getString(1);
                int likes = cursor.getInt(2);
                int approve = cursor.getInt(3);

                Post p = new Post(uid,post,likes,approve);

                arrayList.add(p);
            }
        }

        PostListAdapter postListAdapter = new PostListAdapter(this, R.layout.sample_admin_post_to_approve,arrayList);
        listView.setAdapter(postListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Post p1 = (Post) listView.getItemAtPosition(i);
                myDatabaseHelper.approveThePost(p1.post);
                Toast.makeText(getApplicationContext(),"Approved the post...",Toast.LENGTH_LONG).show();
                listView = findViewById(R.id.listViewIdAdmin);
                loadData();
            }
        });
    }
}
