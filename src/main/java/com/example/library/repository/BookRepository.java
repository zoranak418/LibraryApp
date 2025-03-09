package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> getBooksByAuthor(String author);
    public List<Book> getBooksByTitle(String title);
    public List<Book> getBooksByTitleAndAuthor(String title, String author);
    public List<Book> getBooksByTitleOrAuthor(String title, String author);
    public List<Book> getBooksByGenre(String genre);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    List<Book> findByAuthorContainingIgnoreCase(String author);

}
