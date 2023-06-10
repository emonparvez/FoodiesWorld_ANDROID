package com.tanvir.tanvirmahmudkhan.foodiesworld;

public class Menu {
    public int rid;
    public int fid;
    public String name;
    public String details;
    public String catagory;
    public int price;

    public Menu(int rid, int fid, String name, String details, String catagory, int price) {
        this.rid = rid;
        this.fid = fid;
        this.name = name;
        this.details = details;
        this.catagory = catagory;
        this.price = price;
    }
}
