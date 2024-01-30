package com.example.MPR.JAZ.controllers;
import com.example.MPR.JAZ.entities.House;
import com.example.MPR.JAZ.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/houses")
public class HouseController {
    @Autowired
    private HouseRepository houseRepository;

    @GetMapping
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable Long id) {
        Optional<House> house = houseRepository.findById(id);
        return house.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public House createHouse(@RequestBody House house) {
        return houseRepository.save(house);
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @RequestBody House updatedHouse) {
        if (houseRepository.existsById(id)) {
            updatedHouse.setId(id);
            return ResponseEntity.ok(houseRepository.save(updatedHouse));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable Long id) {
        if (houseRepository.existsById(id)) {
            houseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}