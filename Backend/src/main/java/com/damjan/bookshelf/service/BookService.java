package com.damjan.bookshelf.service;

import com.damjan.bookshelf.model.Book;
import com.damjan.bookshelf.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new EntityNotFoundException("Book with id " + id + " not found");
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findAllByTitleContainingIgnoreCase(title);
    }

//    public List<Book> insertBooks(List<Book> books) {
//        return bookRepository.saveAll(books);
//    }


    public Book updateBook(Integer id, Book updateBook) throws ChangeSetPersister.NotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Book bookToUpdate = optionalBook.get();
        bookToUpdate.setTitle(updateBook.getTitle());
        bookToUpdate.setIsbn(updateBook.getIsbn());
        bookToUpdate.setYear(updateBook.getYear());
        bookToUpdate.setEdition(updateBook.getEdition());
        bookToUpdate.setPublisher(updateBook.getPublisher());
        bookToUpdate.setAuthors(updateBook.getAuthors());
        bookToUpdate.setCategories(updateBook.getCategories());
        bookToUpdate.setFormats(updateBook.getFormats());

        return bookRepository.save(bookToUpdate);
    }
}
