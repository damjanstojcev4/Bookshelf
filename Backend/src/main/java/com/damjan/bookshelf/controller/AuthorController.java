package com.damjan.bookshelf.controller;

import com.damjan.bookshelf.model.Author;
import com.damjan.bookshelf.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById (@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestParam String first_name, @RequestParam String last_name) {
        try {
            Author author = authorService.createAuthor(first_name, last_name);
            return ResponseEntity.ok().body(author);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the author");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id, @RequestParam String first_name,
                                          @RequestParam String last_name) {
        try {
            authorService.updateAuthor(id, first_name, last_name);
            return ResponseEntity.ok().body("Author updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the Author");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorsById(@PathVariable Integer id) {
        try {
            authorService.deleteAuthorsById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
