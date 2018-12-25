package com.alonso.spring.oauth2.controller;

import com.alonso.spring.oauth2.model.User;
import com.alonso.spring.oauth2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<User> getAll() {
        return this.userService.loadAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    User getUserByUserName(@PathVariable long id) {
        return userService.loadUserById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody UserDetails userDetails) {
        throw new UnsupportedOperationException();
//        userService.create(userDetails);
//        HttpHeaders headers = new HttpHeaders();
//        ControllerLinkBuilder linkBuilder = linkTo(methodOn(UserController.class).get(userDetails.getUserName()));
//        headers.setLocation(linkBuilder.toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody User userDetails) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}/assignAuthority")
    @ResponseStatus(value = HttpStatus.OK)
    public void assignAuthority(@PathVariable long id, @RequestBody long authorityId) {
        userService.assignAuthority(id, authorityId);
    }

    @PutMapping("/{id}/revokeAuthority")
    @ResponseStatus(value = HttpStatus.OK)
    public void revokeAuthority(@PathVariable long id,  @RequestBody long authorityId) {
        userService.revokeAuthority(id, authorityId);
    }
}