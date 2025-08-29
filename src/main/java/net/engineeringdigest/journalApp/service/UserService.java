package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.crypto.password.PasswordEncoder.*;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();

    public void saveEntry(User user){
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);            //entry saved in user
    }

    public void saveNewUser(User user){
        //user.setPassword(passwordencoder.encode(user.getPassword()));   // password encode krke waps se user me set krdo
       // user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
     public void deleteById(ObjectId id){
         userRepository.deleteById(id);
     }

     public User findByUserName (String userName){
        return userRepository.findByUserName(userName);
     }

}
