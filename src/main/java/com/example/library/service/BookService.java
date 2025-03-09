package com.example.library.service;

import com.example.library.dto.CreateBookRequest;
import com.example.library.dto.UpdateBookRequest;
import com.example.library.mapper.DtoMapper;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByAuthor(String author){
        return bookRepository.getBooksByAuthor(author);
    }

    public Book saveBook(CreateBookRequest createBookRequest) {
        Book savedBook = DtoMapper.createBookRequestToBook(createBookRequest);
        return bookRepository.save(savedBook);
    }

    public Book updateBook(UpdateBookRequest updateBookRequest, Book book) {
        if(updateBookRequest.getTitle() != null) {
            book.setTitle(updateBookRequest.getTitle());
        }
        if(updateBookRequest.getAuthor() != null) {
            book.setAuthor(updateBookRequest.getAuthor());
        }
        if(updateBookRequest.getGenre() != null) {
            book.setGenre(updateBookRequest.getGenre());
        }
        bookRepository.save(book);
        return book;
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> searchByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
}
