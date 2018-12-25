package com.alonso.spring.oauth2.service;

import com.alonso.spring.oauth2.model.User;
import com.alonso.spring.oauth2.repository.UserRepository;
import com.alonso.spring.oauth2.service.component.UserAuthoritiesComponent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthoritiesComponent userAuthoritiesComponent;

    public UserService(UserRepository userRepository, UserAuthoritiesComponent userAuthoritiesComponent) {
        this.userRepository = userRepository;
        this.userAuthoritiesComponent = userAuthoritiesComponent;
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('IAM_USER_READ')")
    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('IAM_USER_READ')")
    public User loadUserById(long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_USER_DELETE')")
    public void deleteUserById(long id){
        userRepository.delete(id);
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_AUTHORITY_ASSIGN')")
    public void assignAuthority(long userId, long authorityId) {
        User user = userRepository.findOne(userId);
        userRepository.save(userAuthoritiesComponent.assignAuthority(user,authorityId));
    }

    @Transactional
    @PreAuthorize("hasAuthority('IAM_AUTHORITY_REVOKE')")
    public void revokeAuthority(long userId, long authorityId) {
        User user = userRepository.findOne(userId);
        userRepository.save(userAuthoritiesComponent.revokeAuthority(user, authorityId));
    }
}
