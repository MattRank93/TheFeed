package com.interactions.thefeed.security.services;

import com.interactions.thefeed.model.User;
import com.interactions.thefeed.repository.UserRepository;
import com.interactions.thefeed.security.models.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user;
        try {
            user = userRepository.findOneUserByName(username);
        } catch (UsernameNotFoundException ex) {
            throw(new UsernameNotFoundException("User Not Found with username: " + username));
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        }
        return UserDetailsImpl.build(user);
    }
}