package com.example.books.Controller;

import com.example.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private  final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
                new Book(1,"Comp Sci Pro", "Chad Darby", "comp science", 5),
                new Book(2,"Java Spring Master", "Eric Roby", "comp science", 5),
                new Book(3,"Why 2+2 Rocks", "Author A.", "Math", 4),
                new Book(4,"How Bears Hibernate", "Author A.", "Zoology", 3),
                new Book(5,"A Pirate's Treasure", "Author B.", "Fiction", 7),
                new Book(6,"Why 1+1 Rocks", "Author C.", "Math", 8)


        ));
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
        }

    @PostMapping
    public void createBook(@RequestBody Book newBook) {
            
        boolean isNewBook = books.stream()
            .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
                
            if (isNewBook) {
                books.add(newBook);
            }
            
        }

    @PutMapping("/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook) {
        for (int i=0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.set(i, updatedBook);
                return;
            }
        }
        
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));

    }
        
    }


