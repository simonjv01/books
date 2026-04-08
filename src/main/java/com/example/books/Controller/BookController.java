package com.example.books.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @GetMapping("/api-endpoint")
    public String firstApi() {
        return "Hello World";
    }

}
