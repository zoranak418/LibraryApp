package com.example.library.controller;


import com.example.library.model.Copy;
import com.example.library.service.CopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/copies")
public class CopyController {

    private final CopyService copyService;

    @GetMapping
    public List<Copy> getAllCopies() {
        return copyService.getAllCopies();
    }

    @GetMapping("/{id}")
    public Optional<Copy> getCopyById(@PathVariable Long id) {
        return copyService.getCopyById(id);
    }

    @PostMapping
    public Copy createCopy(@RequestBody Copy copy) {
        return copyService.saveCopy(copy);
    }

    @DeleteMapping("/{id}")
    public void deleteCopy(@PathVariable Long id) {
        copyService.deleteCopyById(id);
    }
}
