package com.abd.journalApp.controller;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.abd.journalApp.entity.JournalEntry;
import com.abd.journalApp.entity.User;
import com.abd.journalApp.service.JournalEntryService;
import com.abd.journalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/journal")
@Slf4j
public class JournalControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByName(userName);
                List<JournalEntry> all=user.getJournalEntries();
                if(all!=null && !all.isEmpty())
                {
            return new ResponseEntity<>(all,HttpStatus.OK);
                }

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        log.info("Started Authentication process");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User got Authenticated");
        String userName=authentication.getName();
        log.info("Adding JornalEntry for");
        journalEntryService.saveEntry(myEntry,userName);
        log.debug("Debug");
        return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
    }
    @GetMapping("id/{myID}")
    public ResponseEntity<JournalEntry> getbyID(@PathVariable ObjectId myID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByName(userName);
        List<JournalEntry> list = user.getJournalEntries().stream().filter(x -> x.getId().equals(myID)).toList();
        if(!list.isEmpty()) {

            Optional<JournalEntry> journalEntry = journalEntryService.findById(myID);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myID}")
    public ResponseEntity<?> deleteByID(@PathVariable ObjectId myID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        journalEntryService.deletebyID(myID,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myID}")
    public ResponseEntity<?> updateByID(@PathVariable ObjectId myID,@RequestBody JournalEntry newEntry){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user = userService.findByName(userName);
        JournalEntry oldEntry=journalEntryService.findById(myID).orElse(null);
        List<JournalEntry> list = user.getJournalEntries().stream().filter(x -> x.getId().equals(myID)).toList();
        if(oldEntry!=null && !list.isEmpty())
        {
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
