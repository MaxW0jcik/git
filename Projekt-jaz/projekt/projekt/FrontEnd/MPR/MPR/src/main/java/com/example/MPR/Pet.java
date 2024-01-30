package com.example.MPR;
import java.util.List;

public class Pet {

    private Long id;

    private String name = "";

    private String noise = "";

    public Pet(Long id, String name, String noise) {
        this.id=id;
        this.name = name;
        this.noise = noise;
    }

    public Pet() {
        this.name = "";
        this.noise = "";
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    private List<House> houses;

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
