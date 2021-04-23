package com.spring.jpa_many_to_one.model;

import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {
    public static Specification<Author> getAuthorsByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Author_.firstName), name);
    }
}
