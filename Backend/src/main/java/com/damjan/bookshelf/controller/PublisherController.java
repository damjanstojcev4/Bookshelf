package com.damjan.bookshelf.controller;

import com.damjan.bookshelf.model.Publisher;
import com.damjan.bookshelf.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }
}
