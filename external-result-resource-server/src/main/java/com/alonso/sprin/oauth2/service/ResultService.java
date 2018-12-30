package com.alonso.sprin.oauth2.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ResultService {

//    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('EXTERNAL_RESULT_READ')")
    public List<String> getAllResults() {
        return Arrays.asList("1","2");
    }

//    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('EXTERNAL_RESULT_READ')")
    public String getResultById(long id) {
        return String.valueOf(id);
    }

//    @Transactional
    @PreAuthorize("hasAuthority('EXTERNAL_RESULT_UPDATE')")
    public void modifyResult() {
        //Fake method modify
    }
}
