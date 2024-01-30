package com.example.MPR;

import com.example.MPR.exception.UserAlreadyExistsException;
import org.apache.catalina.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.desktop.UserSessionEvent;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MyRestServiceTest {
    @Mock
    private UserRepository repository;
    private AutoCloseable openMocks;
    private MyRestService myrestService;
    @BeforeEach
    public void init(){
        openMocks = MockitoAnnotations.openMocks(this);
        myrestService = new MyRestService(repository);
    }
    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }
    @Test
    public void findFinds(){
        String username = "Tymon";
        UserDTO user = new UserDTO(username, "tymon@gmail.com",18);
        when(repository.findByUsername(username)).thenReturn(user);

        UserDTO result = myrestService.findByUsername(username);
        assertEquals(user, result);
    }
    @Test
    public void saveSaves(){
        String username = "Maciek";
        String email = "maciek@gmail.com";
        int age = 34;
        UserDTO user = new UserDTO(username, email, age);
        ArgumentCaptor<UserDTO> captor = ArgumentCaptor.forClass(UserDTO.class);
        when(repository.save(captor.capture())).thenReturn(user);

        myrestService.add(user);
        Mockito.verify(repository, Mockito.times(1))
                .save(Mockito.any());
        UserDTO userFromSaveCall = captor.getValue();
        assertEquals(user, userFromSaveCall);
    }
    @Test
    public void userAddThrowsExceptionWhenUserIsPresent(){
        UserDTO user = new UserDTO("Piotr", "piotr@gmail.com",27);
        user.setId(3L);

        when(repository.findById(3L)).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, ()-> {
            myrestService.add(user);
        });
    }
}
