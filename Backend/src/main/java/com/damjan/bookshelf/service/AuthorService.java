package com.damjan.bookshelf.service;

import com.damjan.bookshelf.model.Author;
import com.damjan.bookshelf.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Transactional
    public Author createAuthor(String first_name, String last_name) {
        Author author = new Author();
        author.setFirstName(first_name);
        author.setLastName(last_name);
        return authorRepository.save(author);
    }

    @Transactional
    public void updateAuthor(Integer id, String first_name, String last_name) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setFirstName(first_name);
            author.setLastName(last_name);
            authorRepository.save(author);
        } else {
            throw new EntityNotFoundException("Author with id " + id + " not found");
        }
    }

    @Transactional
    public void deleteAuthorsById(Integer id) {
        authorRepository.deleteById(id);
    }
}
