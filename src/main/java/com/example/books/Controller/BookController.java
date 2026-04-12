package com.example.books.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return books;
    }

}
