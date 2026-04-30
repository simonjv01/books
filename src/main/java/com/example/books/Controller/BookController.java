package com.example.books.Controller;

import com.example.books.entity.Book;
import com.example.books.request.BookRequest;

import jakarta.validation.constraints.Min;

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

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @Min(value = 1) long id) {
     
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
        }


    @PutMapping("/{id}")
    public void updateBook(@PathVariable @Min(value = 1)long id, @RequestBody BookRequest bookRequest) {
        for (int i=0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Book updatedBook = convertToBook(id, bookRequest);
                books.set(i, updatedBook);
                return;
            }
        }
        
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable @Min(value = 1)long id) {
        books.removeIf(book -> book.getId() == id);

    }

    @PostMapping()
    public void createBook(@RequestBody BookRequest bookRequest) {
        long id = books.isEmpty() ? 1 : books.get(books.size() - 1).getId() + 1;
        

        Book book = convertToBook(id, bookRequest);

        books.add(book);
        }


        private Book convertToBook(@Min(value =1 )long id, BookRequest bookRequest) {
            return new Book(
                id, bookRequest.getTitle(), bookRequest.getAuthor(),
                bookRequest.getCategory(), bookRequest.getRating()
            );
        }
    }
        
    


