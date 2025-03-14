package com.example.library.mapper;

import com.example.library.dto.*;
import com.example.library.model.*;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class DtoMapper {


    public static User registerRequestToUser(UserRegisterRequest userRegisterRequest) {
        return User.builder()
                .username(userRegisterRequest.getUsername())
                .password(userRegisterRequest.getPassword())
                .name(userRegisterRequest.getName())
                .surname(userRegisterRequest.getSurname())
                .email(userRegisterRequest.getEmail())
                .build();
    }

    public static Book BookDTOToBook(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .genre(bookDTO.getGenre())
                .build();
    }

    public static Book createBookRequestToBook(CreateBookRequest createBookRequest, Genre genre) {
        return Book.builder()
                .title(createBookRequest.getTitle())
                .author(createBookRequest.getAuthor())
                .genre(genre)
                .build();
    }

    public static Lease createLeaseRequestToLease(CreateLeaseRequest createLeaseRequest, User user, Copy copy) {
        return Lease.builder()
                .fromDate(createLeaseRequest.getFromDate())
                .toDate(createLeaseRequest.getToDate())
                .comments(copy.getBook().getTitle() + ", " + copy.getBook().getAuthor() + ", kopija: " + copy.getId())
                .copy(copy)
                .user(user)
                .build();

    }

    public static Copy createCopyRequestToCopy(CreateCopyRequest createCopyRequest, Book book) {
        return Copy.builder()
                .book(book)
                .build();
    }

    public static DeleteBookResponse deleteBookResponsetoBook(Book book) {
        return DeleteBookResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .message("Success")
                .build();
    }
}
