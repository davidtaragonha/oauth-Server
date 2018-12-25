package com.alonso.spring.oauth2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDetailsService userDetailsService;

    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserDetails> getAll() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{userName}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserDetails getUserByUserName(@PathVariable String userName) {
        return userDetailsService.loadUserByUsername(userName);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody UserDetails userDetails) {
        throw new UnsupportedOperationException();
//        userDetailsService.create(userDetails);
//        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(UserController.class).get(userDetails.getUserName()));
//        headers.setLocation(linkBuilder.toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody UserDetails userDetails) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{userName}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable String userName) {
        throw new UnsupportedOperationException();
    }
}