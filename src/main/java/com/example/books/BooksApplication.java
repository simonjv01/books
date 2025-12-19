package com.example.books;

import com.example.controller.BookController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApplication {

	public static void main(String[] args) {

        SpringApplication.run(BooksApplication.class, args);

        BookController controller = new BookController();
        String response = controller.firstApi();
        System.out.println(response);

	}

}
