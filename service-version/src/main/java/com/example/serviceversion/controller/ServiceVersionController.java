package com.example.serviceversion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceVersionController {

    @GetMapping(value="test")
    String test() {
        return "test";
    }

}