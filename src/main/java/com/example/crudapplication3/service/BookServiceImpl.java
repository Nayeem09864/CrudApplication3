package com.example.crudapplication3.service;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;

    @Autowired
    public void BookService(BookRepository bookRepository) {
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

    @Transactional
    public Book getBookByTitle(@PathVariable String title) {
//        ArrayList<Book> bookList = bookRepository.findAll();
//        return bookList[0];
        Book book=bookRepository.findBookByTitle(title);
        if(book!=null) {
            return bookRepository.findBookByTitle(title);
        }
        else {
            return null;
        }

    }

    public Book addBookByCheckingTitle(@RequestBody Book book) {
        Book book1= bookRepository.addBookByCheckingTitle(book.getTitle());
        if(book1==null) {
            return bookRepository.save(book);
        }
        else {
            System.out.println("Already exists this title");
            return null;
        }
    }
}
