package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PostListAdapter extends ArrayAdapter<Post> {

    private Context mContext;
    int mResource;

    public PostListAdapter(@NonNull Context context, int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String uid = getItem(position).userName;
        String post = getItem(position).post;
        int likes = getItem(position).likes;
        int approve = getItem(position).approve;

        Post p = new Post(uid,post,likes,approve);

        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView userName = convertView.findViewById(R.id.PUserName);
        TextView ppost = convertView.findViewById(R.id.PuttedPost);
        TextView plike = convertView.findViewById(R.id.Plikes);

        userName.setText(uid);
        ppost.setText(post);
        plike.setText(likes+" likes ...");


        return convertView;
    }
}
