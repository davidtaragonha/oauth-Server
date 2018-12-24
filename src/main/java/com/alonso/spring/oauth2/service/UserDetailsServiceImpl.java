package com.alonso.spring.oauth2.service;

import com.alonso.spring.oauth2.model.security.User;
import com.alonso.spring.oauth2.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//TODO Create new public service to CRUD operations via API
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return Optional.ofNullable(user)
                       .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_READ')")
    public UserDetails loadUserById(long id) {
        User user = userRepository.findOne(id);

        return Optional.ofNullable(user)
                .orElseThrow(() -> new UsernameNotFoundException(String.valueOf(id)));
    }

    @Transactional
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public void deleteUserById(long id){
        userRepository.delete(id);
    }
}
