package com.alonso.spring.oauth2.service;

import com.alonso.spring.oauth2.model.Authority;
import com.alonso.spring.oauth2.repository.AuthorityRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('AUTHORITY_READ')")
    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('AUTHORITY_READ')")
    public List<Authority> getByModule(String module) {
        return authorityRepository.findByModule(module);
    }
}
