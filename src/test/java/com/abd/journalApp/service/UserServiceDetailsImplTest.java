/*
package com.abd.journalApp.service;

import com.abd.journalApp.repository.UserRepository;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;


import java.awt.desktop.UserSessionEvent;
import java.util.ArrayList;

import static  org.mockito.Mockito.*;
import static org.springframework.security.core.userdetails.User.*;
import static org.springframework.security.core.userdetails.User.builder;

//@SpringBootTest
public class UserServiceDetailsImplTest {

    //@Autowired
    @InjectMocks
    private UserServiceDetailsImpl userServiceDetails;

    //@MockBean
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
            MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().username("Abd").build());
        UserDetails user=userServiceDetails.loadUserByUsername("Abd");
        Assertions.assertNotNull(user);
    }

}
*/
