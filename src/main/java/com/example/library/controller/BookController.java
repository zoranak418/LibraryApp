package com.example.library.controller;

import com.example.library.dto.CreateBookRequest;
import com.example.library.dto.DeleteBookResponse;
import com.example.library.dto.UpdateBookRequest;
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
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
       // return bookService.searchByTitle(title);
        //todo
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/status")
    public Long isAvailable(@PathVariable Long id) {

        return bookService.isAvailable(id);
    }

    @PostMapping
    public Book createBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.saveBook(createBookRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {
        if(!bookService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Book book = bookService.findById(id).get();
        return ResponseEntity.ok(bookService.updateBook(updateBookRequest, book));
    }

    @DeleteMapping("/{id}")
    public DeleteBookResponse deleteBook(@PathVariable Long id) {
        if(!bookService.findById(id).isPresent()) {
            return DeleteBookResponse.builder()
                    .title("")
                    .author("")
                    .message("Not found")
                    .build();
        }
        Book book = bookService.findById(id).get();
        bookService.delete(id);
        return DtoMapper.deleteBookResponsetoBook(book);
    }
}