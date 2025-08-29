package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService. findByUserName(userName);                 //USER KO NIKALA
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);    //JournalEntry ko SAVE KR DI HAI , aur usko local variable(saved) me save kr diya
            user.getJournalEntries().add(saved);                             //jo user find hua tha uski journal entry me add kra di , reference create ho gaya
            //user.setUserName(null);
            userService.saveEntry(user) ;                                   //aur phir user ko databse me save kra diya hai , with new journal entry
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("an error has occured ",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
     public void deleteById(ObjectId id, String userName){
         User user = userService. findByUserName(userName);               //we found the user
         user.getJournalEntries().removeIf(x -> x.getId().equals(id));
         userService.saveEntry(user);                                        //updates user will be saved
         journalEntryRepository.deleteById(id);
     }
}
