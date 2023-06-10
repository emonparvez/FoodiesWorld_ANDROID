package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfMenu extends AppCompatActivity {

    ListView listViewMenu;
    MyDatabaseHelper myDatabaseHelper;
    int desiredRestaurantId;
    RatingBar ratingBar;
    ImageButton submitRating,location,website;
    public static boolean fromRating=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_menu);


        final Intent intent = getIntent();
        desiredRestaurantId = Integer.parseInt(intent.getStringExtra("desiredRestaurantId")) ;

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submitRating = (ImageButton) findViewById(R.id.Submit);
        location = (ImageButton) findViewById(R.id.location);
        website = (ImageButton) findViewById(R.id.website);


        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = (int) ratingBar.getRating();
                try{
                    myDatabaseHelper.giveRating(n,desiredRestaurantId);
                    fromRating=true;

                    Intent intent1 = new Intent(ListOfMenu.this,ListOfRestaurant.class);
                    startActivity(intent1);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Exception: "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String restaurantName = myDatabaseHelper.getRestaurantName(desiredRestaurantId);
                String geoURI = String.format("geo:%f,%f?q="+restaurantName,99.0,99.0);
                Uri geo = Uri.parse(geoURI);

                Intent geoMap = new Intent(Intent.ACTION_VIEW,geo);
                startActivity(geoMap);
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String website_str = myDatabaseHelper.getWebsite(desiredRestaurantId);
                String RestaurantName_str = myDatabaseHelper.getRestaurantName(desiredRestaurantId);

                Intent intent1 = new Intent(ListOfMenu.this,Website.class);
                intent1.putExtra("website",website_str);
                intent1.putExtra("restaurantName",RestaurantName_str);
                startActivity(intent1);
            }
        });


        listViewMenu = (ListView) findViewById(R.id.listViewIdMenu);
        myDatabaseHelper = new MyDatabaseHelper(this);

        loadData();
    }

    public void loadData() {
        ArrayList<com.tanvir.tanvirmahmudkhan.foodiesworld.Menu> arrayList = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.getMenu(desiredRestaurantId);

        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No menu to show...",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                int rid = cursor.getInt(0);
                int fid = cursor.getInt(1);
                String name = cursor.getString(2);
                String details = cursor.getString(3);
                String catagory = cursor.getString(4);
                int price = cursor.getInt(5);


                com.tanvir.tanvirmahmudkhan.foodiesworld.Menu menu = new com.tanvir.tanvirmahmudkhan.foodiesworld.Menu(rid,fid,name,details,catagory,price);

                arrayList.add(menu);
            }
        }

        MenuListAdapter menuListAdapter = new MenuListAdapter(this, R.layout.sample_menu_view,arrayList);
        listViewMenu.setAdapter(menuListAdapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                com.tanvir.tanvirmahmudkhan.foodiesworld.Menu m = (com.tanvir.tanvirmahmudkhan.foodiesworld.Menu) listViewMenu.getItemAtPosition(i);
            }
        });


    }
}
