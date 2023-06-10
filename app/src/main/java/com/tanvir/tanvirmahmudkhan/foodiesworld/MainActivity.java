package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper myDatabaseHelper;

    EditText name, pass;
    Button signIn, signUp;

    static ArrayList<Integer> drawableImageIds;
    static ArrayList<String> renames;

    static int rid = 1;
    static int fid = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // All the table will be created in constructor
        myDatabaseHelper= new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        // Now insert values

        // Adding Restaurant to database

        myDatabaseHelper.insertRestaurant(rid++,"Tanvir Foods","Dhaka","Mirpur","Rupnagar R/A, Mirpur-2, Dhaka",5.0,1,"");
        myDatabaseHelper.insertRestaurant(rid++,"Tour De Cyclists","Dhaka","Mirpur","Beside Yantai Chinese Restaurant, Mirpur-11, Dhaka",5.0,1,"https://www.facebook.com/tdcyclist/");
        myDatabaseHelper.insertRestaurant(rid++,"Coffee Time","Dhaka","Mirpur","Beside Popular Diagonistic Center, Mirpur-6, Dhaka",5.0,1,"http://www.coffeetime.com");
        myDatabaseHelper.insertRestaurant(rid++,"Foodies","Dhaka","Banani","Banani-11, Dhaka",5.0,1,"https://foodee.com.bd");
        myDatabaseHelper.insertRestaurant(rid++,"Cafe Entro","Dhaka","Banani","Banani-11, Dhaka",5.0,1,"https://www.facebook.com/cafeentrobd");
        myDatabaseHelper.insertRestaurant(rid++,"Euphoria","Dhaka","Banani","Banani-11, Dhaka",5.0,1,"https://www.facebook.com/cafeeuphoria");
        myDatabaseHelper.insertRestaurant(rid++,"Chilekotha","Dhaka","Banani","Banani-8, Dhaka",5.0,1,"https://www.facebook.com/Chilekotha.Restaurant");

        // Adding Menu to database

        myDatabaseHelper.insertMenu(1,fid++,"Chicken Burger","Cheese & Mayo, juicy burger, one layer chicken...","Burger",200);
        myDatabaseHelper.insertMenu(1,fid++,"Beef Burger","Cheese & Mayo, juicy burger, one layer Beef...","Burger",230);
        myDatabaseHelper.insertMenu(1,fid++,"Mashroom Burger","Cheese & Mayo, juicy burger, one layer chicken with Mashroom...","Burger",250);
        myDatabaseHelper.insertMenu(1,fid++,"Naga Burger","Cheese & Mayo, juicy burger, one layer chicken with naga sauce...","Burger",300);

        myDatabaseHelper.insertMenu(2,fid++,"Spicy  Chicken Burger","Spicy Cheese & Mayo, juicy burger, one layer chicken...","Burger",200);
        myDatabaseHelper.insertMenu(2,fid++,"Spicy Beef Burger","Spicy Cheese & Mayo, juicy burger, one layer Beef...","Burger",230);
        myDatabaseHelper.insertMenu(2,fid++,"Spicy Mashroom Burger","Spicy Cheese & Mayo, juicy burger, one layer chicken with Mashroom...","Burger",250);
        myDatabaseHelper.insertMenu(2,fid++,"Spicy Naga Burger","Spicy Cheese & Mayo, juicy burger, one layer chicken with naga sauce...","Burger",300);

        myDatabaseHelper.insertMenu(3,fid++,"Hot Chicken Burger","Hot Cheese & Mayo, juicy burger, one layer chicken...","Burger",200);
        myDatabaseHelper.insertMenu(3,fid++,"Hot Beef Burger","Cheese & Mayo, juicy burger, one layer Beef...","Burger",230);
        myDatabaseHelper.insertMenu(3,fid++,"Hot Mashroom Burger","Cheese & Mayo, juicy burger, one layer chicken with Mashroom...","Burger",250);
        myDatabaseHelper.insertMenu(3,fid++,"Hot Naga Burger","Cheese & Mayo, juicy burger, one layer chicken with naga sauce...","Burger",300);

        myDatabaseHelper.insertMenu(4,fid++,"Naga Chicken Burger","Naga Cheese & Mayo, juicy burger, one layer chicken...","Burger",200);
        myDatabaseHelper.insertMenu(4,fid++,"Naga Beef Burger","Cheese & Mayo, juicy burger, one layer Beef...","Burger",230);
        myDatabaseHelper.insertMenu(4,fid++,"Naga Mashroom Burger","Cheese & Mayo, juicy burger, one layer chicken with Mashroom...","Burger",250);
        myDatabaseHelper.insertMenu(4,fid++,"Naga Naga Burger","Cheese & Mayo, juicy burger, one layer chicken with naga sauce...","Burger",300);

        myDatabaseHelper.insertMenu(5,fid++,"Naga Chicken Burger","Naga Cheese & Mayo, juicy burger, one layer chicken...","Burger",200);
        myDatabaseHelper.insertMenu(5,fid++,"Naga Beef Burger","Cheese & Mayo, juicy burger, one layer Beef...","Burger",230);
        myDatabaseHelper.insertMenu(5,fid++,"Naga Mashroom Burger","Cheese & Mayo, juicy burger, one layer chicken with Mashroom...","Burger",250);
        myDatabaseHelper.insertMenu(5,fid++,"Naga Naga Burger","Cheese & Mayo, juicy burger, one layer chicken with naga sauce...","Burger",300);

        myDatabaseHelper.insertMenu(6,fid++,"Naga Chicken Burger","Naga Cheese & Mayo, juicy burger, one layer chicken...","Burger",200);
        myDatabaseHelper.insertMenu(6,fid++,"Naga Beef Burger","Cheese & Mayo, juicy burger, one layer Beef...","Burger",230);
        myDatabaseHelper.insertMenu(6,fid++,"Naga Mashroom Burger","Cheese & Mayo, juicy burger, one layer chicken with Mashroom...","Burger",250);
        myDatabaseHelper.insertMenu(6,fid++,"Naga Naga Burger","Cheese & Mayo, juicy burger, one layer chicken with naga sauce...","Burger",300);

        myDatabaseHelper.insertMenu(7,fid++,"Naga Chicken Burger","Naga Cheese & Mayo, juicy burger, one layer chicken...","Burger",200);
        myDatabaseHelper.insertMenu(7,fid++,"Naga Beef Burger","Cheese & Mayo, juicy burger, one layer Beef...","Burger",230);
        myDatabaseHelper.insertMenu(7,fid++,"Naga Mashroom Burger","Cheese & Mayo, juicy burger, one layer chicken with Mashroom...","Burger",250);
        myDatabaseHelper.insertMenu(7,fid++,"Naga Naga Burger","Cheese & Mayo, juicy burger, one layer chicken with naga sauce...","Burger",300);

        Integer[] drawableImageIds1= {R.drawable.tanvirfoods,R.drawable.tourdecyclists,R.drawable.coffeetime,R.drawable.foodies,R.drawable.cafeentro,R.drawable.euphoria,R.drawable.chilekotha};
        String[] renames1 = {"tanvirfoods","tourdecyclists","coffeetime","foodies","cafeentro","euphoria","chilekotha"};

        // Admin User
        myDatabaseHelper.insertUser("admin","Admin","admin","sajid8212@gmail.com","Admin");

        // Restaurant photo
        drawableImageIds = new ArrayList<Integer>();
        renames = new ArrayList<String> ();

        drawableImageIds.addAll(Arrays.asList(drawableImageIds1));
        renames.addAll(Arrays.asList(renames1));

        // ******** END of DataBase ************

        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.SignIn);
        signUp = (Button) findViewById(R.id.SignUp);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("admin") && myDatabaseHelper.checkValidity(name.getText().toString(),pass.getText().toString())){ // Super user name & password for admin... If someone use this uname & pass he will login as an admin
                    Toast.makeText(getApplicationContext(),"Successfully logged in as an ADMIN... <3",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, AdminHome.class);
                    startActivity(intent);
                }
                else if(myDatabaseHelper.checkValidity(name.getText().toString(),pass.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Successfully logged in... <3",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, FirstPage.class);
                    intent.putExtra("UID", name.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong user name or password... <3",Toast.LENGTH_LONG).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });





    }
}
