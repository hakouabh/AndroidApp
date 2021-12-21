package com.example.roomapplication.Model.Entities;


public class Product {

    private String name;

    private double buying_price;

    private double selling_price;

    private int quantity;

    private String image;

    private String sku;

    public Product(String name, double buying_price, double selling_price, int quantity, String image, String sku) {
        this.name = name;
        this.buying_price = buying_price;
        this.selling_price = selling_price;
        this.quantity = quantity;
        this.image = image;
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(float buying_price) {
        this.buying_price = buying_price;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(float selling_price) {
        this.selling_price = selling_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

}