package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewMenu extends AppCompatActivity {
    Spinner rnameSpinner;
    EditText name,details,catagory,price;
    String Sname,Sdetails,Scatagory,Sprice;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_menu);

        name = (EditText) findViewById(R.id.name);
        details = (EditText) findViewById(R.id.details);
        catagory = (EditText) findViewById(R.id.catagory);
        price = (EditText) findViewById(R.id.price);
        add = (Button) findViewById(R.id.add);


        final MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);

        rnameSpinner =(Spinner) findViewById(R.id.resNameSpinner);

        ArrayList<String> rnameList = myDatabaseHelper.getAllRestaurantName();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, rnameList);

        rnameSpinner.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sname = name.getText().toString();
                Sdetails = details.getText().toString();
                Scatagory = catagory.getText().toString();
                Sprice = price.getText().toString();
                String rn = rnameSpinner.getSelectedItem().toString();

                int iprice = Integer.parseInt(Sprice);

                int rid = myDatabaseHelper.getRIDbyRname(rn);

                myDatabaseHelper.insertMenu(rid,MainActivity.fid++, Sname,Sdetails,Scatagory,iprice);

                Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();

                name.setText("");
                details.setText("");
                catagory.setText("");
                price.setText("");

            }
        });


    }
}
