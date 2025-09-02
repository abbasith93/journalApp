package com.abd.journalApp.service;

import com.abd.journalApp.entity.User;
import com.abd.journalApp.repository.UserRepository;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder PASSWORD_ENCODER=new BCryptPasswordEncoder();

    public void saveNewUser(User user){

        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        log.info("role set to USER");
        userRepository.save(user);
        log.info("user added with role USER");
    }

    public void saveAdmin(User user){

        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

        public void saveUser(User user)
        {
        userRepository.save(user);
            }

    public void deleteByUserName(String username){
        userRepository.deleteByUserName(username);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findByName(String username){
        return userRepository.findByUserName(username);
    }

   /* public void deletebyID(ObjectId id){
        journalEntryRepository.deleteById(id);
    }*/

}
