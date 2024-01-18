package com.damjan.bookshelf.repository;

import com.damjan.bookshelf.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    List<Publisher> findAllById(Integer publisherId);
    List<Publisher> findAll();

}
