package com.example.library.service;


import com.example.library.model.Copy;
import com.example.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CopyService {
    private final CopyRepository copyRepository;

    public List<Copy> getAllCopies() { return copyRepository.findAll(); }
    public Optional<Copy> getCopyById(Long id) { return copyRepository.findById(id); }
    public Copy saveCopy(Copy copy) { return copyRepository.save(copy); }
    public void deleteCopyById(Long id) { copyRepository.deleteById(id); }

}
