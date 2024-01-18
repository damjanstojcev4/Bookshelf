package com.damjan.bookshelf.repository;

import com.damjan.bookshelf.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByTitleContainingIgnoreCase(String title);
    //List<Book> findAllByYear(Integer year);
    List<Book> findAllByPublisher_Id(Integer publisherId);
    List<Book> findAllByAuthors_Id(Integer authorId);
    List<Book> findAllByCategories_Id(Integer categoryId);
    List<Book> findAllByFormats_Id(Integer formatId);
    List<Book> findByIsbn(String isbn);

    @Modifying
    @Query("UPDATE Book b SET b.title = :title, b.isbn = :isbn, b.year = :year, b.edition = :edition, b.publisher = :publisher, " +
            "b.authors = :authors, b.categories = :categories, b.formats = :formats WHERE b.id = :id")
    int updateBook(@Param("id") Integer id,
                   @Param("title") String title,
                   @Param("isbn") String isbn,
                   @Param("year") String year,
                   @Param("edition") String edition,
                   @Param("publisher") String publisher,
                   @Param("authors") List<String> authors,
                   @Param("categories") List<String> categories,
                   @Param("formats") List<String> formats);
}
