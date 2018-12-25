package com.alonso.spring.oauth2.service;

import com.alonso.spring.oauth2.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_READ')")
    public UserDetails loadUserById(long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public void deleteUserById(long id){
        userRepository.delete(id);
    }
}
