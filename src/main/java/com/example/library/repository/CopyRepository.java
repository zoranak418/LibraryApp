package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

    public List<Copy> findByBook(Book book);
}
