package com.tanvir.tanvirmahmudkhan.foodiesworld;

public class Post {

    String userName;
    String post;
    int likes;
    int approve;

    public Post(String userName, String post,int likes,int approve) {
        this.userName = userName;
        this.post = post;
        this.likes = likes;
        this.approve = approve;
    }
}
