package com.example.books.Controller;

import com.example.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private  final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("Title one", "Author One", "science"),
                new Book("Title two", "Author Two", "fiction"),
                new Book("Title three", "Author Three", "history"),
                new Book("Title four", "Author Four", "science"),
                new Book("Title five", "Author Five", "fiction"),
                new Book("Title six", "Author Six", "math"),
                new Book("Title seven", "Author Seven", "math")

        ));
    }

    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
        }

        public void createBook(@RequestBody Book newBook) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(newBook.getTitle())) {
                    return; // Book with the same title already exists, do not add
                }
            }
        }
        
    }


