package com.example.crudapplication3.service;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService{
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }

    @Transactional
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    @Transactional
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(existingBook);
        }
        return null;
    }


    @Transactional
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

}
