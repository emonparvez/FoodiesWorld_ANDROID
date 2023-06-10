package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuListAdapter  extends ArrayAdapter<Menu> {

    private Context mContext;
    int mResource;

    public MenuListAdapter(@NonNull Context context, int resource, @NonNull List<Menu> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int rid = getItem(position).rid;
        int fid = getItem(position).rid;
        String name = getItem(position).name;
        String details = getItem(position).details;
        String catagory = getItem(position).catagory;
        int price = getItem(position).price;

        Menu menu = new Menu(rid,fid,name,details,catagory,price);

        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView fname = (TextView) convertView.findViewById(R.id.FoodName);
        TextView fprice = (TextView) convertView.findViewById(R.id.FoodPrice);
        TextView fdetails = (TextView) convertView.findViewById(R.id.FoodDetails);

        fname.setText(name);
        fprice.setText("Price: "+price);
        fdetails.setText(details);


        return convertView;
    }
}
