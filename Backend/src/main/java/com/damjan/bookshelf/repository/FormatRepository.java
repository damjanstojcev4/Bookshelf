package com.damjan.bookshelf.repository;

import com.damjan.bookshelf.model.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormatRepository extends JpaRepository<Format, Integer> {
    List<Format> findAllById(Integer formatId);
    List<Format> findAll();
}
