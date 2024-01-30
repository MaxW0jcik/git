package com.example.MPR.JAZ.services;

import com.example.MPR.JAZ.entities.Pet;
import com.example.MPR.JAZ.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> findAllPets(){return (List<Pet>)this.petRepository.findAll();}
    public Optional<Pet> findPetById(Integer id)
    {
        Optional<Pet> pet= this.petRepository.findPetById(id);
        if (pet.isPresent()){
            return pet;
        }
        else {
            throw new PetNotFoundException();
        }
    }
    public Pet addPet(Pet body)
    {
        Optional<Pet> pet = this.petRepository.findPetById(body.getId());
        if (pet.isPresent()) {
            throw new PetAlreadyExistsException();
        }
        return this.petRepository.save(body);
    }
    public void deletePet(Integer id) {
        Optional<Pet> pet= this.petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new PetNotFoundException();
        }
        this.petRepository.deleteById(id);
    }
    public void updatePet(Pet body) {
        Optional<Pet> pet = this.petRepository.findPetById(body.getId());
        if(pet.isPresent())
        {
            Pet presentPet = pet.get();
            presentPet.setId(body.getId());
            presentPet.setPetName(body.getPetName());
            this.petRepository.save(presentPet);
        }
    }
}

