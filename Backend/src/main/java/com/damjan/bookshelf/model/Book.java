package com.damjan.bookshelf.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private Character edition;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "book_format",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "format_id"))
    private Set<Format> formats;


    public Book() {
    }

    public Book(String title, String isbn, String year, Character edition, Publisher publisher,
                Set<Author> authors, Set<Category> categories, Set<Format> formats) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.edition = edition;
        this.publisher = publisher;
        this.authors = authors;
        this.categories = categories;
        this.formats = formats;
    }

    public Book(Integer id, String title, String isbn, String year, Character edition, Publisher publisher,
                Set<Author> authors, Set<Category> categories, Set<Format> formats) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.edition = edition;
        this.publisher = publisher;
        this.authors = authors;
        this.categories = categories;
        this.formats = formats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Character getEdition() {
        return edition;
    }

    public void setEdition(Character edition) {
        this.edition = edition;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Format> getFormats() {
        return formats;
    }

    public void setFormats(Set<Format> formats) {
        this.formats = formats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year='" + year + '\'' +
                ", edition=" + edition +
                ", publisher=" + publisher +
                ", authors=" + authors +
                ", categories=" + categories +
                ", formats=" + formats +
                '}';
    }
}
