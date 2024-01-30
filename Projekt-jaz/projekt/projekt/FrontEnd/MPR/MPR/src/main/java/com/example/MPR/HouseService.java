package com.example.MPR;

import com.example.MPR.House;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class HouseService {

    public static final String API_URL = "http://localhost:8080";
    RestClient restClient;
    public HouseService() {
        restClient = RestClient.create();
    }

    public Iterable<House> findAllHouses(){
        List<House> houses = restClient
                .get()
                .uri(API_URL + "/houses/get/all")
                .retrieve()
                //.body(House.class) // <- jeden obiekt
                .body(new ParameterizedTypeReference<>(){});
        return houses;
    }
    public House findById(Integer id)
    {
        House house = restClient
                .get()
                .uri(API_URL + "/houses/get/" + id)
                .retrieve()
                .body(House.class);
        if (house == null) {
            throw new HouseNotFoundException();
        }
        else {
            return house;
        }
    }
    public House findHouseById(Integer id)
    {
       House house = restClient
                .get()
                .uri(API_URL + "/houses/get/" + id)
                .retrieve()
                .body(House.class);
        if (house == null) {
            throw new HouseNotFoundException();
        }
        else {
            return house;
        }
    }
    public Void addHouse(House body) {
        ResponseEntity<Void> house = restClient
                .post()
                .uri(API_URL + "/pets/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
        if (house != null) {
            return house.getBody();
        }
        else {
            throw new HouseAlreadyExistsException();
        }
    }
    public void deleteHouse(Integer id) {
        restClient
                .delete()
                .uri(API_URL + "/houses/delete/" + id)
                .retrieve()
                .toBodilessEntity();
    }
    public Void updateHouse(House body)
    {
        ResponseEntity<Void> house = restClient
                .put()
                .uri(API_URL + "/houses/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
        if (house != null) {
            return house.getBody();
        }
        else {
            throw new HouseAlreadyExistsException();
        }
    }
}
