/*
package com.abd.journalApp.controller;

import com.abd.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalController {

   private Map<String,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }
    @GetMapping("id/{myID}")
    public JournalEntry getbyID(@PathVariable String myID){
        return journalEntries.get(myID);
    }

    @DeleteMapping("id/{myID}")
    public JournalEntry deleteByID(@PathVariable String myID){
        return journalEntries.remove(myID);
    }

    @PutMapping("id/{myID}")
    public JournalEntry updateByID(@PathVariable String myID,@RequestBody JournalEntry journalEntry){
        return journalEntries.put(myID,journalEntry);
    }
}
*/
