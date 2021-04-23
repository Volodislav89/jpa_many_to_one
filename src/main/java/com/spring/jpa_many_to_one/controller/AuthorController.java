package com.spring.jpa_many_to_one.controller;

import com.spring.jpa_many_to_one.model.Author;
import com.spring.jpa_many_to_one.model.Book;
import com.spring.jpa_many_to_one.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/id/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/name")
    public List<Author> getAuthorsByFirstName(@RequestParam("name") String name) {
        return authorService.getAuthorsByFirstName(name);
    }

    @PostMapping
    public Author saveNewAuthor(@RequestBody Author author) {
        return authorService.saveNewAuthor(author);
    }

    @PutMapping("add/{id}")
    public Author addBookToAuthor(@PathVariable Long id, @RequestBody Book book) {
       return authorService.addBookToAuthor(id, book);
    }

    @PatchMapping
    public Author addAuthorToBook(@RequestParam("id") Long bookId, @RequestBody Author author) {
        return authorService.addAuthorToBook(bookId, author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/id/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
    }

    @DeleteMapping
    public void deleteAllAuthors() {
        authorService.deleteAllAuthors();
    }
}
