package com.abd.journalApp.controller;

import com.abd.journalApp.entity.User;
import com.abd.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Admin class
   /* public ResponseEntity<List<User>> getAll()
    {
                List<?> all=userService.getEntry();
                if(all!=null && !all.isEmpty())
                {
            return new ResponseEntity<>(userService.getEntry(),HttpStatus.OK);
                }

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
/*
    @PostMapping
    public ResponseEntity<User> createEntry(@RequestBody User user){
        userService.saveNewUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }*/
   /* @GetMapping("id/{myID}")
    public ResponseEntity<JournalEntry> getbyID(@PathVariable ObjectId myID){
        Optional<JournalEntry> journalEntry=journalEntryService.findById(myID);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    /*@DeleteMapping("id/{myID}")
    public ResponseEntity<?> deleteByID(@PathVariable ObjectId myID){
        journalEntryService.deletebyID(myID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @PutMapping
    public ResponseEntity<?> updateByID(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userInDB=userService.findByName(username);
        if(userInDB!=null)
        {
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveNewUser(userInDB);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> DeleteUser(){
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            userService.deleteByUserName(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
