package com.example.MPR;

import com.example.MPR.exception.UserNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class
MyRestService {
    public static final String BASE_URL = "http://localhost:8080";
    RestClient restClient;
    public MyRestService() {
        restClient = RestClient.create();
    }

    public Iterable<UserDTO> findAll(){
        List<UserDTO> users = restClient
                .get()
                .uri(BASE_URL + "/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return users;
    }
    public UserDTO findById(Long id) {
            UserDTO userDTO = restClient
                    .get()
                    .uri(BASE_URL + "/users/{id}", id)
                    .retrieve()
                    .body(UserDTO.class);

            if(userDTO!=null){
                return userDTO;
        }
            else {
                throw new UserNotFoundException("User not found");
            }
    }

    public void deleteById(Long id) {
        restClient
                .delete()
                .uri(BASE_URL + "/users/delete/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public Void addUser(UserDTO body){
        ResponseEntity<Void> user = restClient
                .post()
                .uri(BASE_URL + "/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
        return user.getBody();
    }
    public void updateUserById(UserDTO user, long id){
        restClient
                .put()
                .uri(BASE_URL + "/users/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .toBodilessEntity();
    }

}
