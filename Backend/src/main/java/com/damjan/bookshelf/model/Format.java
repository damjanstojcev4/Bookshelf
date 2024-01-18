package com.damjan.bookshelf.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "format")
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "format", nullable = false)
    private String format;

    public Format() {
    }

    public Format(String format) {
        this.format = format;
    }

    public Format(Integer id, String format) {
        this.id = id;
        this.format = format;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Format format = (Format) o;
        return Objects.equals(id, format.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Format{" +
                "id=" + id +
                ", format='" + format + '\'' +
                '}';
    }
}
