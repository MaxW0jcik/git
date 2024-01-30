package com.example.MPR;
public class House {

    private Long id;

    private String owner = "";

    private double price = 0.0;

    public House(Long id, String owner, double price) {
        this.id=id;
        this.owner = owner;
        this.price = price;
    }

    public House() {
        this.owner= "";
        this.price = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
