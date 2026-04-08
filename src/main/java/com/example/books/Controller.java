package com.example.books;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/api-endpoint")
    public String firstApi() {
        return "Hello World";
    }

}
