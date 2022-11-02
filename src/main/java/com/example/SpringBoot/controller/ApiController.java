package com.example.SpringBoot.controller;

import com.example.SpringBoot.common.util.Utility;
import com.example.SpringBoot.model.FlightResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping(value = "/api/*")
@Validated
public class ApiController {
    private HttpHeaders headers = new HttpHeaders();
    private FlightResponse flightResponse = new FlightResponse();

    @GetMapping(value = "/flight", produces = "application/json; charset=UTF8")
    public ResponseEntity Get()
    {
        String response = RunService();

        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping(value = "/flight", produces = "application/json; charset=UTF8")
    public ResponseEntity Post()
    {

        String response = RunService();

        return ResponseEntity.ok().headers(headers).body(response);
    }

    public String RunService(){
        String response = "";

        try{
            headers.add("Content-Type", "application/json");

            response = Utility.JsonSerialize(flightResponse);
        }catch (Exception e){
        }

        return response;
    }
}

