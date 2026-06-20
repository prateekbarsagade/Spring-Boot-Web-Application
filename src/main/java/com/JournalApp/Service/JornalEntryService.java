package com.JournalApp.Service;

import com.JournalApp.Entity.JournalEntry;
import com.JournalApp.Entity.User;
import com.JournalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JornalEntryService {



    private JournalEntryRepository journalEntryRepository;

    public JornalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    @Autowired
    private UserService userService;


//    @Transactional   // we need to use mongodb atlas . as we get error replica set
    public void saveEntry(JournalEntry journalEntry, String username){
        try{
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
//            user.setUsername(null);
            userService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error Occur while saving the entry");
        }
    }

    public void saveEntry(JournalEntry journalEntry){
         journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

//    @Transactional
    public boolean deleteEntry(ObjectId id, String username){

        boolean removed = false;

        try{
            User user = userService.findByUsername(username);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occur while deleting the id " + e);
        }

        return removed;
    }


//    public List<JournalEntry> findByUsername(String username){
//
//    }





}
