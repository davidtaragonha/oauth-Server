package com.alonso.spring.oauth2.controller;

import com.alonso.spring.oauth2.model.User;
import com.alonso.spring.oauth2.service.UserService;
import com.alonso.spring.oauth2.view.UserView;
import com.alonso.spring.oauth2.view.UserViewMapper;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final UserViewMapper userViewMapper;

    public UserController(UserService userService, UserViewMapper userViewMapper) {
        this.userService = userService;
        this.userViewMapper = userViewMapper;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<UserView> getAll() {
        return this.userService.getAllUsers().stream()
                .map(userViewMapper::userToUserView)
                .collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserView getUserById(@PathVariable long id) {
        return userViewMapper.userToUserView(userService.getUserById(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody UserView userView) {
        User user = userService.createUser(userView.getUsername(), userView.getPassword());

        HttpHeaders headers = new HttpHeaders();
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(UserController.class).getUserById(user.getId()));
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody UserView userView) {
        userService.modifyUser(userView.getId(), userView.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}/assignAuthority")
    @ResponseStatus(value = HttpStatus.OK)
    public void assignAuthority(@PathVariable long id, @RequestBody long authorityId) {
        userService.assignAuthorityToUser(id, authorityId);
    }

    @PutMapping("/{id}/revokeAuthority")
    @ResponseStatus(value = HttpStatus.OK)
    public void revokeAuthority(@PathVariable long id,  @RequestBody long authorityId) {
        userService.revokeAuthorityToUser(id, authorityId);
    }
}