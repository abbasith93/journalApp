package com.abd.journalApp.service;

import com.abd.journalApp.entity.JournalEntry;
import com.abd.journalApp.entity.User;
import com.abd.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByName(userName);

            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
            log.info("JournalEntries got saved in user");
        }
        catch (Exception e){
            throw new RuntimeException("Entity no saved", e);
        }
    }
    
    public List<JournalEntry> getEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deletebyID(ObjectId id, String username){

        try
        {
            User user=userService.findByName(username);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
        } catch (Exception e) {
            log.error("User failed to add",username,e);
            throw new RuntimeException("Entity cannot be deleted",e);
        }

    }

    public void saveEntry(JournalEntry oldEntry)
    {
        journalEntryRepository.save(oldEntry);
    }
}
