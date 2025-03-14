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

    public static UserResponse createUserToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }

    public static Book BookDTOToBook(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .genre(bookDTO.getGenre())
                .build();
    }

    public static BookResponse BookToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre().toString())
                .build();
    }

    public static BookStatusResponse createBooktoBookStatusResponse(Book book, Long available) {
        return BookStatusResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .available(available)
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
                .comments(createLeaseRequest.getComments())
                .copy(copy)
                .user(user)
                .build();

    }

    public static LeaseResponse createLeaseToLeaseResponse(Lease lease) {
        return LeaseResponse.builder()
                .id(lease.getId())
                .fromDate(lease.getFromDate())
                .toDate(lease.getToDate())
                .comments(lease.getComments())
                .title(lease.getCopy().getBook().getTitle())
                .author(lease.getCopy().getBook().getAuthor())
                .copy(lease.getCopy().getId())
                .user(lease.getUser().getName() + " " + lease.getUser().getSurname())
                .build();

    }

    public static Lease createUpdateLeaseToLease(UpdateLeaseRequest updateLeaseRequest) {
        return Lease.builder()
                .toDate(updateLeaseRequest.getToDate())
                .comments(updateLeaseRequest.getComments())
                .build();
    }

    public static CopyResponse createCopyToCopyResponse(Copy copy) {
        return CopyResponse.builder()
                .id(copy.getId())
                .title(copy.getBook().getTitle())
                .author(copy.getBook().getAuthor())
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
