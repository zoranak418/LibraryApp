package com.example.library.controller;

import com.example.library.dto.*;
import com.example.library.mapper.DtoMapper;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{author}")
    public List<BookResponse> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/title/{title}")
    public List<BookResponse> getBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/{id}/status")
    public BookStatusResponse isAvailable(@PathVariable Long id) {
        return bookService.howManyAvailable(id);
    }

    @PostMapping
    public BookResponse createBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.saveBook(createBookRequest);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {
        Book book = bookService.getById(id);
        return bookService.updateBook(updateBookRequest, book);
    }

    @DeleteMapping("/{id}")
    public DeleteBookResponse deleteBook(@PathVariable Long id) {
        if(bookService.getBookById(id) == null) {
            return DeleteBookResponse.builder()
                    .title("")
                    .author("")
                    .message("Not found")
                    .build();
        }
        Book book = bookService.getById(id);
        bookService.delete(id);
        return DtoMapper.deleteBookResponsetoBook(book);
    }
}