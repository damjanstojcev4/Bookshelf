package com.damjan.bookshelf.service;

import com.damjan.bookshelf.model.Format;
import com.damjan.bookshelf.repository.FormatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormatService {
    @Autowired
    private FormatRepository formatRepository;

    public FormatService(FormatRepository formatRepository) {
        this.formatRepository = formatRepository;
    }

    public List<Format> getAllFormats() {
        return formatRepository.findAll();
    }

    @Transactional
    public Format createFormat(String format) {
        Format format1 = new Format();
        format1.setFormat(format);
        return formatRepository.save(format1);
    }

    @Transactional
    public void updateFormat(Integer id, String format) {
        Optional<Format> optionalFormat = formatRepository.findById(id);
        if (optionalFormat.isPresent()) {
            Format format1 = optionalFormat.get();
            format1.setId(id);
            format1.setFormat(format);
            formatRepository.save(format1);
        } else {
            throw new EntityNotFoundException("Format with id " + id + " not found");
        }
    }

    @Transactional
    public void deleteFormatById(Integer id) {
        formatRepository.deleteById(id);
    }
}
