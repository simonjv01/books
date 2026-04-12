package com.example.books.Controller;

import com.example.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private  final List<Book> books = new ArrayList<>();
    

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("Title one", "Author One", "science"),
                new Book("Title two", "Author Two", "fiction"),
                new Book("Title three", "Author Three", "history"),
                new Book("Title four", "Author Four", "science"),
                new Book("Title five", "Author Five", "fiction")

        ));
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return books;
    }

}
