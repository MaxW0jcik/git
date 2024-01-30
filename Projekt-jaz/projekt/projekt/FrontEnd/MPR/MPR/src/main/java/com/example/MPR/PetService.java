package com.example.MPR;

import com.example.MPR.Pet;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PetService {

    public static final String API_URL = "http://localhost:8080";
    RestClient restClient;
    public PetService() {
        restClient = RestClient.create();
    }

    public Iterable<Pet> findAllPets(){
        List<Pet> pets = restClient
                .get()
                .uri(API_URL + "/pets/get/all")
                .retrieve()
                //.body(Pet.class) // <- jeden obiekt
                .body(new ParameterizedTypeReference<>(){});
        return pets;
    }
    public Pet findById(Integer id)
    {
        Pet pet = restClient
                .get()
                .uri(API_URL + "/pets/get/" + id)
                .retrieve()
                .body(Pet.class);
        if (pet == null) {
            throw new PetNotFoundException();
        }
        else {
            return pet;
        }
    }
    public Pet findPetById(Integer id)
    {
        Pet pet = restClient
                .get()
                .uri(API_URL + "/pets/get/" + id)
                .retrieve()
                .body(Pet.class);
        if (pet == null) {
            throw new PetNotFoundException();
        }
        else {
            return pet;
        }
    }
    public Void addPet(Pet body) {
        ResponseEntity<Void> pet = restClient
                .post()
                .uri(API_URL + "/pets/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
        if (pet != null) {
            return pet.getBody();
        }
        else {
            throw new PetAlreadyExistsException();
        }
    }
    public void deletePet(Integer id) {
        restClient
                .delete()
                .uri(API_URL + "/pets/delete/" + id)
                .retrieve()
                .toBodilessEntity();
    }
    public Void updatePet(Pet body)
    {
        ResponseEntity<Void> pet = restClient
                .put()
                .uri(API_URL + "/pets/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
        if (pet != null) {
            return pet.getBody();
        }
        else {
            throw new PetAlreadyExistsException();
        }
    }
}
