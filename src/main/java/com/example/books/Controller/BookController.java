package com.example.books.Controller;

import com.example.books.entity.Book;
import com.example.books.request.BookRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Books Rest API Endpoints", description = "Operations related to Books")
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
                new Book(6,"Why 1+1 Rocks", "Author C.", "Math", 8),
                new Book(7,"Game of Thrones", "GRR Martin", "Fantasy", 9)


        ));
    }

    @Operation(summary = "Get all books", description = "Retrieve a list of all books, optionally filtered by category.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(@Parameter(description = "Optional query parameter") @RequestParam(required = false) String category) {
        if (category == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @Operation(summary = "Get book by ID", description = "Retrieve a single book by its unique ID.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @Min(value = 1) long id) {
     
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
        }
    
    @Operation(summary = "Update an existing book", description = "Update the details of an existing book by its ID.")    
    @ResponseStatus(HttpStatus.NO_CONTENT)   
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

    @Operation(summary = "Delete a book", description = "Remove a book from the collection by its ID.") 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable @Min(value = 1)long id) {
        books.removeIf(book -> book.getId() == id);

    }

    @Operation(summary = "Create a new book", description = "Add a new book to the collection.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
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
        
    


