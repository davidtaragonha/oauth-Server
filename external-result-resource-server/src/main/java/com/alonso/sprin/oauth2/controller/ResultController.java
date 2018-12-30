package com.alonso.sprin.oauth2.controller;

import com.alonso.sprin.oauth2.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<String> getAll() {
        return resultService.getAllResults();
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    String getById(@PathVariable long id) {
        return resultService.getResultById(id);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    void update() {
        resultService.modifyResult();
    }
}