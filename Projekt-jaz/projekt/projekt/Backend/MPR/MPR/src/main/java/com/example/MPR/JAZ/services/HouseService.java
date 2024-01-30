package com.example.MPR.JAZ.services;

import com.example.MPR.JAZ.entities.Pet;
import com.example.MPR.JAZ.repositories.HouseRepository;
import com.example.MPR.JAZ.entities.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> findAllHouses(){return (List<House>)this.houseRepository.findAll();}
    public Optional<House> findHouseById(Integer id)
    {
        Optional<House> house= this.houseRepository.findHouseById(id);
        if (house.isPresent()){
            return house;
        }
        else {
            throw new HouseNotFoundException();
        }
    }
    public House addHouse(House body)
    {
        Optional<House> house = this.houseRepository.findHouseById(body.getId());
        if (house.isPresent()) {
            throw new HouseAlreadyExistsException();
        }
        return this.houseRepository.save(body);
    }
    public void deleteHouse(Integer id) {
        Optional<House> house= this.houseRepository.findById(id);
        if (house.isEmpty()) {
            throw new HouseNotFoundException();
        }
        this.houseRepository.deleteById(id);
    }
    public void updateHouse(House body) {
        Optional<House> house = this.houseRepository.findHouseById(body.getId());
        if(house.isPresent())
        {
            House presentHouse = house.get();
            presentHouse.setId(body.getId());
            presentHouse.setHouseOwner(body.getHouseOwner());
            this.houseRepository.save(presentHouse);
        }
    }
}

}