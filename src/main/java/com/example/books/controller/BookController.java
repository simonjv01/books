package com.example.books.controller;

import com.example.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    // Immutable list of sample books
    private final List<Book> books = List.of(
        new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"),
        new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"),
        new Book("1984", "George Orwell", "Dystopian"),
        new Book("A Brief History of Time", "Stephen Hawking", "Science"),
        new Book("The Art of War", "Sun Tzu", "Philosophy")
    );

    // Default constructor kept for clarity; no initialization logic required
    public BookController() {
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return books;
    }
}
