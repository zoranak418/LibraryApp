package com.example.library.service;


import com.example.library.dto.CopyResponse;
import com.example.library.dto.CreateCopyRequest;
import com.example.library.dto.UpdateBookRequest;
import com.example.library.dto.UpdateCopyRequest;
import com.example.library.mapper.DtoMapper;
import com.example.library.model.Book;
import com.example.library.model.Copy;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CopyService {
    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;

    public List<CopyResponse> getAllCopies() {
        List<Copy> copies = copyRepository.findAll();
        List<CopyResponse> copiesResponse = new ArrayList<CopyResponse>();
        for (Copy copy : copies) {
            copiesResponse.add(DtoMapper.createCopyToCopyResponse(copy));
        }
        return copiesResponse;
    }


    public Copy getCopyById(Long id) {
        return copyRepository.findById(id).orElse(null);
    }

    public CopyResponse findResponseById(Long id) {
        Copy copy = copyRepository.findById(id).orElse(null);
        return DtoMapper.createCopyToCopyResponse(copy);
    }

    public List<CopyResponse> saveCopy(CreateCopyRequest copyRequest) {
        Book book = bookRepository.findById(copyRequest.getBook()).get();
        Long count = copyRequest.getCount();
        List<Copy> copies = new ArrayList<>();
        List<CopyResponse> copiesResponse = new ArrayList<>();

        while(count > 0) {
            Copy savedCopy = copyRepository.save(DtoMapper.createCopyRequestToCopy(copyRequest, book));
            copies.add(savedCopy);
            copiesResponse.add(DtoMapper.createCopyToCopyResponse(savedCopy));
            count --;
        }
        return copiesResponse;
    }



    public void deleteCopyById(Long id) {
        copyRepository.deleteById(id);
    }

}
