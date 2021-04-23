package com.spring.jpa_many_to_one.repository;

import com.spring.jpa_many_to_one.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
