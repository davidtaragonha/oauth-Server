package com.alonso.sprin.oauth2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/external")
public class ExternalController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<String> getAll() {
        return Arrays.asList("1","2");
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    String getByModule(@PathVariable String id) {
        return id;
    }
}