package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/api-endpoint")
    public String firstApi() {
        return "Hello Simon!";
    }
}
