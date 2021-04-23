package com.spring.jpa_many_to_one.service;

import com.spring.jpa_many_to_one.model.Author;
import com.spring.jpa_many_to_one.model.Book;
import com.spring.jpa_many_to_one.repository.AuthorRepository;
import com.spring.jpa_many_to_one.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.spring.jpa_many_to_one.model.AuthorSpecification.getAuthorsByName;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("No author with such id: %d", id)));
    }

    public List<Author> getAuthorsByFirstName(String name) {
        return authorRepository.findAll(getAuthorsByName(name));
    }

    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author addBookToAuthor(Long authorId, Book book) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException(String.format("No author with such id: %d", authorId)));
        author.addBook(book);
        book.setAuthor(author);
        return authorRepository.save(author);
    }

    public Author addAuthorToBook(Long bookId, Author author) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException(String.format("No book with such id: %d", bookId)));
//        Author newAuthor = authorRepository.save(author);
//        author.addBook(book);
        book.setAuthor(author);
        Author newAuthor = bookRepository.save(book).getAuthor();
        return authorRepository.findById(newAuthor.getAuthorId()).get();
    }

    @Transactional
    public Author updateAuthor(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("No author with such id: %d", id)));
        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());
        existingAuthor.setMiddleName(author.getMiddleName());
        return authorRepository.save(existingAuthor);
    }

    @Transactional
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }
}
