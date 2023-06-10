package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class FirstPage extends AppCompatActivity {

    Button search,discussion;
    AutoCompleteTextView city,area;
    public static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Intent intent = getIntent();
        uid = intent.getStringExtra("UID");

        discussion = (Button) findViewById(R.id.discussion);
        search = (Button) findViewById(R.id.Search);
        city = (AutoCompleteTextView) findViewById(R.id.City);
        area = (AutoCompleteTextView) findViewById(R.id.Area);

        String[] area_arr1 =getResources().getStringArray(R.array.area_arr);
        String[] city_arr1 =getResources().getStringArray(R.array.city_arr);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,area_arr1);
        area.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,city_arr1);
        city.setAdapter(adapter1);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_city = city.getText().toString();
                String str_area = area.getText().toString();

                Intent intent = new Intent(FirstPage.this, ListOfRestaurant.class);
                intent.putExtra("City",str_city);
                intent.putExtra("Area",str_area);
                startActivity(intent);
            }
        });

        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstPage.this, Home.class);
                startActivity(intent);
            }
        });


    }

}
