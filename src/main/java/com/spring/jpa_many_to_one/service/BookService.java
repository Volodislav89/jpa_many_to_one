package com.spring.jpa_many_to_one.service;

import com.spring.jpa_many_to_one.model.Book;
import com.spring.jpa_many_to_one.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("No book with such id: %d", id)));
    }

    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("No book with such id: %d", id)));
        existingBook.setTitle(book.getTitle());
        existingBook.setTotalPages(book.getTotalPages());
        existingBook.setPublisher(book.getPublisher());
        return bookRepository.save(existingBook);
    }

    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}
