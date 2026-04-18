package com.example.books.Controller;

import com.example.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


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

    @PostMapping("/api/books")
    public void createBook(@RequestBody Book newBook) {
            
        boolean isNewBook = books.stream()
            .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
                
            if (isNewBook) {
                books.add(newBook);
            }
            
        }

    @PutMapping("api/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook) {
        //TODO: process PUT request
        for (int i=0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.set(i, updatedBook);
                return;
            }
        }
        
    }

    @DeleteMapping("api/books/{title}")
    public void deleteBook(@PathVariable String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        
    }
        
    }


