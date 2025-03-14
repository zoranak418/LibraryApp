package com.example.library.service;

import com.example.library.dto.BookResponse;
import com.example.library.dto.BookStatusResponse;
import com.example.library.dto.CreateBookRequest;
import com.example.library.dto.UpdateBookRequest;
import com.example.library.mapper.DtoMapper;
import com.example.library.model.Book;
import com.example.library.model.Copy;
import com.example.library.model.Genre;
import com.example.library.model.Lease;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CopyRepository;
import com.example.library.repository.LeaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final LeaseRepository leaseRepository;
    private final CopyRepository copyRepository;

    public List<BookResponse> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : books) {
            bookResponses.add(DtoMapper.BookToBookResponse(book));
        }
        return bookResponses;
    }

    public BookResponse getBookById(Long id) {
        return DtoMapper.BookToBookResponse(bookRepository.findById(id).orElse(null));
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<BookResponse> getBooksByAuthor(String author){
        List<Book> books = bookRepository.getBooksByAuthor(author);
        List<BookResponse> bookResponses = new ArrayList<BookResponse>();
        for (Book book : books) {
            bookResponses.add(DtoMapper.BookToBookResponse(book));
        }
        return bookResponses;
    }

    public List<BookResponse> getBooksByGenre(String genre){
        List<Book> books = bookRepository.getBooksByGenre(genre);
        List<BookResponse> bookResponses = new ArrayList<BookResponse>();
        for (Book book : books) {
            bookResponses.add(DtoMapper.BookToBookResponse(book));
        }
        return bookResponses;
    }

    public List<BookResponse> getBooksByTitle(String title){
        List<Book> books = bookRepository.getBooksByTitle(title);
        List<BookResponse> bookResponses = new ArrayList<BookResponse>();
        for (Book book : books) {
            bookResponses.add(DtoMapper.BookToBookResponse(book));
        }
        return bookResponses;
    }

    public BookResponse saveBook(CreateBookRequest createBookRequest) {
        Genre genre = createBookRequest.getGenre();
        Book savedBook = DtoMapper.createBookRequestToBook(createBookRequest, genre);
        return DtoMapper.BookToBookResponse(bookRepository.save(savedBook));
    }

    public BookStatusResponse howManyAvailable(Long id) {
        List<Lease> leases = leaseRepository.findByBookActive(id);
        Long numOfActiveCopies = (long) leases.size();
        Long numOfCopies = (long) copyRepository.findByBookId(id).size();
        Book book = bookRepository.findById(id).orElse(null);
        return DtoMapper.createBooktoBookStatusResponse(book, Long.valueOf(numOfCopies - numOfActiveCopies));
    }


    public BookResponse updateBook(UpdateBookRequest updateBookRequest, Book book) {
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
        return DtoMapper.BookToBookResponse(book);
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
