package com.alonso.spring.oauth2.controller;

import com.alonso.spring.oauth2.model.Authority;
import com.alonso.spring.oauth2.service.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Authority> getAll() {
        return authorityService.getAll();
    }

    @GetMapping("module/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Authority> getByModule(@PathVariable String id) {
        return authorityService.getByModule(id);
    }
}