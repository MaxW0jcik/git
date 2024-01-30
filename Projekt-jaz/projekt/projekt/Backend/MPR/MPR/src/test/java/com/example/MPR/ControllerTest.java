package com.example.MPR;

import com.example.MPR.exception.UserExceptionHandler;
import com.example.MPR.exception.UserNotFoundException;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    private MockMvc mocMvc;
    @Mock
    private MyRestService service;
    @InjectMocks
    private MyController controller;
    @BeforeEach
    public void setup(){
        this.mocMvc = MockMvcBuilders.standaloneSetup(
                new UserExceptionHandler(), controller).build();
    }
//    @Test
//    public void getByIdReturns200WhenUserIsPresent() throws Exception{
//        UserDTO user = new UserDTO("Maks", "maks@gmail.com",32);
//        when(service.findById(3L)).thenReturn(Optional.of(user));
//
//        mocMvc.perform(MockMvcRequestBuilders.get("/users/get/3"))
//                .andExpect(jsonPath("$.email").value("maks@gmail.com"))
//                .andExpect(jsonPath("$.username").value("Maks"))
//                .andExpect(status().isOk());
//    }
    @Test
    public void check400IsReturnedWhenUserIsAlreadyThere() throws Exception {

    }


}
