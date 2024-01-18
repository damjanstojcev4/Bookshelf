package com.damjan.bookshelf.repository;

import com.damjan.bookshelf.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAllById(Integer authorId);
    List<Author> findAll();
}
