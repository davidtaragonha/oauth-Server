package com.alonso.spring.oauth2.service;

import com.alonso.spring.oauth2.model.User;
import com.alonso.spring.oauth2.repository.UserRepository;
import com.alonso.spring.oauth2.service.component.UserAuthoritiesComponent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthoritiesComponent userAuthoritiesComponent;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserAuthoritiesComponent userAuthoritiesComponent, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userAuthoritiesComponent = userAuthoritiesComponent;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('IAM_USER_READ')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('IAM_USER_READ')")
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_USER_DELETE')")
    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_USER_CREATE')")
    public User createUser(String userName, String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_USER_UPDATE')")
    public void modifyUser(long id, String userName) {
        User userFromDatabase = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        userFromDatabase.setUsername(userName);
        userRepository.save(userFromDatabase);
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_AUTHORITY_ASSIGN')")
    public void assignAuthorityToUser(long userId, long authorityId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.save(userAuthoritiesComponent.assignAuthority(user,authorityId));
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_AUTHORITY_REVOKE')")
    public void revokeAuthorityToUser(long userId, long authorityId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.save(userAuthoritiesComponent.revokeAuthority(user, authorityId));
    }
}
