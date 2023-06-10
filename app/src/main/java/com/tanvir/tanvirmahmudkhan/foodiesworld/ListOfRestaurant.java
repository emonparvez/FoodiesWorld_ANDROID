package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfRestaurant extends AppCompatActivity {

    ListView listView;
    MyDatabaseHelper myDatabaseHelper;
    static String searched_city,searched_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_restaurant);
        searched_city ="";
        searched_area ="";

        if(!ListOfMenu.fromRating){
            Intent intent = getIntent();
            searched_city = intent.getStringExtra("City");
            searched_area = intent.getStringExtra("Area");
        }

            listView = (ListView) findViewById(R.id.listViewId);
            myDatabaseHelper = new MyDatabaseHelper(this);

            loadData();


    }

    public void loadData() {
        ArrayList<Restaurant> arrayList = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.getAllRestaurant(searched_city,searched_area);

        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No restaurant to show...",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                int rid = cursor.getInt(0);
                String name = cursor.getString(1);
                String city = cursor.getString(2);
                String area = cursor.getString(3);
                String address = cursor.getString(4);
                double rating = cursor.getDouble(5);
                int totalRated = cursor.getInt(6);

                Restaurant restaurant = new Restaurant(rid,name,city,area,address,rating,totalRated);

                arrayList.add(restaurant);
            }
        }

        RestaurantListAdapter restaurantListAdapter = new RestaurantListAdapter(this, R.layout.sample_restaurant_view,arrayList);
        listView.setAdapter(restaurantListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Restaurant r = (Restaurant) listView.getItemAtPosition(i);

                String str_rid = r.rid+"";

                Intent intent = new Intent(ListOfRestaurant.this, ListOfMenu.class);
                intent.putExtra("desiredRestaurantId",str_rid);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),r.name,Toast.LENGTH_LONG).show();
            }
        });
    }
}
