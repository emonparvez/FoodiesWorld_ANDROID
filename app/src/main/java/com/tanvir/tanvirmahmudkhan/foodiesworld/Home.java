package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    TextView welcomeText;
    EditText status;
    Button submit;
    ListView listViewIdPosts;
    MyDatabaseHelper myDatabaseHelper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;
        myDatabaseHelper = new MyDatabaseHelper(context);

        welcomeText =(TextView) findViewById(R.id.welcomeText);
        status = (EditText) findViewById(R.id.Status);
        submit = (Button) findViewById(R.id.submit);
        listViewIdPosts = (ListView) findViewById(R.id.listViewIdPosts);

        String userName = myDatabaseHelper.getUserName(FirstPage.uid);
        welcomeText.setText("Welcome, Mr "+userName);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_status = status.getText().toString();

                myDatabaseHelper.insertPosts(FirstPage.uid,str_status,0,0);

                Toast.makeText(context,"Thanks for your post... Your post will be approve soon by an andmin..",Toast.LENGTH_LONG).show();
                status.setText("");
                listViewIdPosts = (ListView) findViewById(R.id.listViewIdPosts);
                loadData();
            }
        });

        loadData();



    }
    public void loadData() {
        ArrayList<Post> arrayList = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.getAllPosts();

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

        PostListAdapter postListAdapter = new PostListAdapter(this, R.layout.sample_post_view,arrayList);
        listViewIdPosts.setAdapter(postListAdapter);

        listViewIdPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Post p1 = (Post) listViewIdPosts.getItemAtPosition(i);
                p1.likes++;
                myDatabaseHelper.updateLikeForPost(p1.post,p1.likes);
                listViewIdPosts = findViewById(R.id.listViewIdPosts);
                loadData();
            }
        });

    }
}
