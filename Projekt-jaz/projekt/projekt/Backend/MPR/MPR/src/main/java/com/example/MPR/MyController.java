package com.example.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class MyController {
    @Autowired
    public MyRestService usersService;
    private final Logger logger = Logger.getLogger("MyController");

    public MyController(MyRestService usersService){
        this.usersService = usersService;
        usersService.add(new UserDTO("maks","maks@gmail.com",18));
        usersService.add(new UserDTO("bartek","bartek@gmail.com",27));
        usersService.add(new UserDTO("szymon","szymon@gmail.com",45));
    }

    @GetMapping("users/{id}")
    public Optional<UserDTO> getById(@PathVariable("id")long id){
        logger.info("Endpoint called: getById");
        return usersService.findById(id);
    }

    @GetMapping("users")
    public List<UserDTO> getAllUsername(){
        logger.info("Endpoint called: getAll");
        return (List<UserDTO>) usersService.findAll();
    }

    @GetMapping("users/name/{name}")
    public UserDTO getByUsername(@PathVariable("name") String username) {
        return usersService.findByUsername(username);
    }

    @GetMapping("users/filterByUsername/{name}")
    public List<UserDTO> filterByUsername(@PathVariable("name") String name) {
        return usersService.filterByUsername(name);
    }

    @PostMapping("users/add")
    public void addUser(@RequestBody UserDTO body)
    {
        logger.info("Endpoint called: add user");
        this.usersService.addUser(body);
    }


    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        Optional<UserDTO> user = usersService.findById(id);
        if(user.isPresent()) {
            usersService.deleteUser(id);
        }
    }

    @PutMapping("/users/update/{id}")
    public void updateUser(@RequestBody UserDTO body) {
        Optional<UserDTO> user = usersService.findById(body.getId());
        if (user.isPresent()) {
            usersService.updateUser(body);
        }
    }

}
