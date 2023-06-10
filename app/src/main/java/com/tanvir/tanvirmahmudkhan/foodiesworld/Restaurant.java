package com.tanvir.tanvirmahmudkhan.foodiesworld;

public class Restaurant {
    public int rid;
    public String name;
    public String city;
    public String area;
    public String address;
    public double rating;
    public int totalRated;

    public Restaurant(int rid, String name, String city, String area, String address, double rating, int totalRated) {
        this.rid = rid;
        this.name = name;
        this.city = city;
        this.area = area;
        this.address = address;
        this.rating = rating;
        this.totalRated = totalRated;
    }
}
