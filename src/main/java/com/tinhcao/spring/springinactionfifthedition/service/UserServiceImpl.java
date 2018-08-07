package com.tinhcao.spring.springinactionfifthedition.service;

import com.tinhcao.spring.springinactionfifthedition.entity.User;
import com.tinhcao.spring.springinactionfifthedition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null != user) {
            return user;
        }
        throw new UsernameNotFoundException("User " + username + " not found");
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
