package com.example.library.mapper;

import com.example.library.dto.CreateBookRequest;
import com.example.library.dto.DeleteBookResponse;
import com.example.library.dto.UserRegisterRequest;
import com.example.library.model.Book;
import com.example.library.model.User;
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

    public static Book createBookRequestToBook(CreateBookRequest createBookRequest) {
        return Book.builder()
                .title(createBookRequest.getTitle())
                .author(createBookRequest.getAuthor())
                .genre(createBookRequest.getGenre())
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
