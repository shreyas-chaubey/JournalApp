package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class userServiceDetailimpleme implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user!=null) {
            return  org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .username(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))    //List has been converted to array of string data type
                    .build();
        }
        throw new UsernameNotFoundException("user not found :" + username);
    }
}
