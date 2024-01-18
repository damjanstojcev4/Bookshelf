package com.damjan.bookshelf.controller;

import com.damjan.bookshelf.model.Format;
import com.damjan.bookshelf.service.FormatService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/formats")
public class FormatController {
    @Autowired
    private FormatService formatService;

    @GetMapping("/")
    public List<Format> getAllFormats() {
        return formatService.getAllFormats();
    }

    @PostMapping
    public ResponseEntity<?> addFormat(@RequestParam String format) {
        try {
            Format format1 = formatService.createFormat(format);
            return ResponseEntity.ok().body(format1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the format");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFormat(@PathVariable Integer id, @RequestParam String format) {
        try {
            formatService.updateFormat(id, format);
            return ResponseEntity.ok().body("Format updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the Format");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFormatById(@PathVariable Integer id) {
        try {
            formatService.deleteFormatById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}