package com.example.MPR.JAZ.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("owner")
    private String owner = "";

    @JsonProperty("price")
    private double price = 0.0;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public House(String owner, double price) {
        this.owner = owner;
        this.price = price;
    }

    public House() {}

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