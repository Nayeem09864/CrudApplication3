package com.example.crudapplication3.controller;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

//    @Autowired
//    public BookController(BookService bookService) {
//        this.bookService=bookService;
//    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("id/{id}")
    public Book getBookById(@PathVariable Long id) {
        //return bookRepository.findById(id).orElse(null);
        return bookService.getBookById(id);
    }

    @GetMapping("/title/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/type/{type}")
    public Book getBookByType(@PathVariable String type) {
        return bookService.getBookByType(type);
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book) {
        //return bookRepository.save(book);
        return bookService.addBook(book);
    }

    @PostMapping("/checkTitle")
    public Book addBookByCheckingTitle(@RequestBody Book book) {
        //return bookRepository.save(book);
        return bookService.addBookByCheckingTitle(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id,updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        //bookRepository.deleteById(id);
        bookService.deleteBook(id);
    }
}
