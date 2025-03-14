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
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/{id}/status")
    public Long isAvailable(@PathVariable Long id) {

        return bookService.howManyAvailable(id);
    }

    @PostMapping
    public Book createBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.saveBook(createBookRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {
        if(bookService.getBookById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(bookService.updateBook(updateBookRequest, book));
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
        Book book = bookService.getBookById(id);
        bookService.delete(id);
        return DtoMapper.deleteBookResponsetoBook(book);
    }
}