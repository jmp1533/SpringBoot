package com.example.SpringBoot.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping(value = "/api/*")
@Validated
public class ApiController {

    @PostMapping(value = "/movie/current", produces = "application/json; charset=UTF8")
    public ResponseEntity Post()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return ResponseEntity.ok().headers(headers).body("");
    }

}

