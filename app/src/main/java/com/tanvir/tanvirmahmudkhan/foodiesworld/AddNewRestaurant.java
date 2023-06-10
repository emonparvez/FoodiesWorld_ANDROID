package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewRestaurant extends AppCompatActivity {
    AutoCompleteTextView city,area;
    EditText name,address,website;
    MyDatabaseHelper myDatabaseHelper;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_restaurant);

        submit = (Button) findViewById(R.id.confirm);
        city = (AutoCompleteTextView) findViewById(R.id.city);
        area =(AutoCompleteTextView) findViewById(R.id.area);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        website = (EditText) findViewById(R.id.webText);

        myDatabaseHelper = new MyDatabaseHelper(this);

        String[] area_arr1 =getResources().getStringArray(R.array.area_arr);
        String[] city_arr1 =getResources().getStringArray(R.array.city_arr);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,area_arr1);
        area.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,city_arr1);
        city.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city_str= city.getText().toString();
                String area_str= area.getText().toString();
                String name_str= name.getText().toString();
                String address_str= address.getText().toString();
                String website_str= website.getText().toString();

                myDatabaseHelper.insertRestaurant(MainActivity.rid++,name_str,city_str,area_str,address_str,5.0,1,website_str);
                Toast.makeText(getApplicationContext(),"Successfully inserted",Toast.LENGTH_LONG);

                startActivity(new Intent(AddNewRestaurant.this,AdminHome.class));
            }
        });

    }
}
