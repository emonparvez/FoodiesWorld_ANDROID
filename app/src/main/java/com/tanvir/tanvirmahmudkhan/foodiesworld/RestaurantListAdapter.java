package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

    private Context mContext;
    int mResource;

    public RestaurantListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Restaurant> objects) {
        super(context, resource, objects);
        mContext =context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int rid = getItem(position).rid;
        String name = getItem(position).name;
        String city = getItem(position).city;
        String area = getItem(position).area;
        String address = getItem(position).address;
        double rating = getItem(position).rating;
        int totalRated = getItem(position).totalRated;

        Restaurant restaurant = new Restaurant(rid,name,city,area,address,rating,totalRated);

        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView rname = (TextView) convertView.findViewById(R.id.RName);
        TextView rarea = (TextView) convertView.findViewById(R.id.RArea);
        TextView rrating = (TextView) convertView.findViewById(R.id.RRating);
        ImageView rimageView = (ImageView) convertView.findViewById(R.id.RimageView);

        rname.setText(name);
        rarea.setText(area+"  "+address);
        rrating.setText("Rating: "+rating);

        String imageName =  name.replaceAll("\\s+","");
        imageName= imageName.toLowerCase();
        boolean found =false;

        for(int i=0; i<MainActivity.drawableImageIds.size(); i++){
            if(MainActivity.renames.get(i).equals(imageName)){
                rimageView.setImageResource(MainActivity.drawableImageIds.get(i));
                found =true;
            }
        }
        if(!found){
            rimageView.setImageResource(R.drawable.default_restaurant);
        }


        return convertView;


    }
}
