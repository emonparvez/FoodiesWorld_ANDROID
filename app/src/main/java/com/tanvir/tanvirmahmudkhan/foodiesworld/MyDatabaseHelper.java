package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String dataBaseName = "FoodiesWorld";

    //Table Create SQL
    private static final String createTable_User ="create table User (UID varchar(100) primary key, Name varchar(200), Password varchar(200), Email varchar(200), Type varchar(100) );";
    private static final String createTable_Restaurant ="create table Restaurant (RID integer primary key autoincrement, Name varchar(200), City varchar(200), Area varchar(200), Address varchar(200), Rating REAL, TotalRated integer, Webstie varchar(200) );";
    private static final String createTable_Menu ="create table Menu (RID integer, FID integer primary key autoincrement, Name varchar(200), Details varchar(200), Catagory varchar(200), Price integer );";
    private static final String createTable_Posts ="create table Posts (UID varchar(100),  Post varchar(1000), Likes integer, Approve integer );";

    private static final int versionNumber = 1;

    private Context context;


    public MyDatabaseHelper(Context context) {
        super(context, dataBaseName, null, versionNumber);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(createTable_User);
            sqLiteDatabase.execSQL(createTable_Restaurant);
            sqLiteDatabase.execSQL(createTable_Menu);
            sqLiteDatabase.execSQL(createTable_Posts);
            Toast.makeText(context, "OnCreate Called...", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context, "OnUpgrade Called...", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("");
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }
    }

    public long insertUser(String uid, String name, String pass, String email, String type ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("UID", uid);
        contentValues.put("Name", name);
        contentValues.put("Password", pass);
        contentValues.put("Email", email);
        contentValues.put("Type", type);

        long rowID = sqLiteDatabase.insert("User",null,contentValues);

        return rowID;
    }

    public long insertRestaurant(int rid, String name, String city, String area, String add, double rating, int totalRated , String Webstie){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("RID", rid);
        contentValues.put("Name", name);
        contentValues.put("City", city);
        contentValues.put("Area", area);
        contentValues.put("Address", add);
        contentValues.put("Rating", rating);
        contentValues.put("TotalRated", totalRated);
        contentValues.put("Webstie", Webstie);

        long rowID = sqLiteDatabase.insert("Restaurant",null,contentValues);
        return rowID;
    }

    public long insertMenu(int rid,int fid, String name, String details, String catagory, int price ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("RID", rid);
        contentValues.put("FID", fid);
        contentValues.put("Name", name);
        contentValues.put("Details", details);
        contentValues.put("Catagory", catagory);
        contentValues.put("Price", price);

        long rowID = sqLiteDatabase.insert("Menu",null,contentValues);
        return rowID;
    }

    public long insertPosts(String uid,String post, int likes, int approve ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("UID", uid);
        contentValues.put("Post", post);
        contentValues.put("Likes", likes);
        contentValues.put("Approve", approve);

        long rowID =  sqLiteDatabase.insert("Posts",null,contentValues);
        return rowID;
    }

    public boolean checkValidity(String uname, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where UID='"+uname+"' AND Password ='"+password+"';",null);

        if(cursor.getCount()!=0){
            //Toast.makeText(context,"Cursor: "+cursor.getCount()+"Username: "+uname,Toast.LENGTH_LONG).show();
            return true;
        }
        else{
            //Toast.makeText(context,"Cursor: "+cursor.getCount()+"Username: "+uname,Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public boolean check_If_Uid_Unique(String uid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where UID='"+uid+"';",null);

        if(cursor.getCount()==0){
            // Yes it is unique..
            return true;
        }
        else {
            // No already exits..
            return false;
        }
    }

    public Cursor getAllRestaurant(String city, String area){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor;

        if(city.equals("") && area.equals("")){
            cursor = sqLiteDatabase.rawQuery("select * from Restaurant;", null);
        }
        else if(city.equals("")){
            cursor = sqLiteDatabase.rawQuery("select * from Restaurant where Area like '%"+area+"%' ;", null);
        }
        else if(area.equals("")){
            cursor = sqLiteDatabase.rawQuery("select * from Restaurant where City like '%"+city+"%' ;", null);
        }
        else{
            cursor = sqLiteDatabase.rawQuery("select * from Restaurant where City like '%"+city+"%' OR Area like '%"+area+"%' ;", null);
        }



        return cursor;
    }
    public Cursor getMenu(int desiredRestaurant){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from Menu where rid="+desiredRestaurant+";", null);
        return cursor;
    }

    public boolean giveRating(int rating, int rid){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Restaurant where rid="+rid+";", null);

        if(cursor.getCount()>0){
            // ****************PROBLEM IS HERE********************************
            cursor.moveToNext();
            double previous_rating = cursor.getDouble(5);
            int previous_totalRated =cursor.getInt(6);

            double total = (previous_rating*previous_totalRated)+rating;
            int new_totalRated = previous_totalRated+1;
            double new_rating = total / new_totalRated;

            try {
                SQLiteDatabase sqLiteDatabase1 = this.getWritableDatabase();
                sqLiteDatabase1.execSQL("UPDATE Restaurant set Rating="+new_rating+", TotalRated="+new_totalRated+" where rid="+rid+";");
            }catch (Exception e){
                Toast.makeText(context,"Exception: "+e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(context,"No restaurant found",Toast.LENGTH_LONG).show();
        }


        return true;

    }

    public String getUserName(String uid){

        String userName="";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from User where uid='"+uid+"';", null);

        if(cursor.getCount()!=0){
            cursor.moveToNext();
            userName = cursor.getString(1);
        }

        return userName;
    }

    public Cursor getAllPosts(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Posts where Approve = 1;", null);
        return cursor;
    }
    public Cursor getAllPostsThatNotApproved(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Posts where Approve = 0;", null);
        return cursor;
    }
    public void approveThePost(String post){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE Posts set Approve=1 where Post='"+post+"';");

    }

    public String getWebsite(int rid){

        String webstite="";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Restaurant where RID="+rid+";", null);

        if(cursor.getCount()!=0){
            cursor.moveToNext();
            webstite = cursor.getString(7);
        }

        return webstite;
    }
    public String getRestaurantName(int rid){

        String rname="";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Restaurant where RID="+rid+";", null);

        if(cursor.getCount()!=0){
            cursor.moveToNext();
            rname = cursor.getString(1);
        }

        return rname;
    }

    public void updateLikeForPost(String post, int updatedLikes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL("UPDATE Posts set Likes="+updatedLikes+" where Post='"+post+"';");
    }

    public ArrayList<String> getAllRestaurantName(){

        ArrayList<String> list=new ArrayList<String>();
        String rname="";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Restaurant;", null);

        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                rname = cursor.getString(1);
                list.add(rname);
            }
        }

        return list;
    }

    public int  getRIDbyRname(String rname){
        int rid=0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Restaurant where Name='"+rname+"';", null);

        if(cursor.getCount()!=0){
            cursor.moveToNext();
            rid = cursor.getInt(0);
        }

        return rid;

    }


}
