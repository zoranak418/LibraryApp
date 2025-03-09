package com.example.library.service;


import com.example.library.model.Book;
import com.example.library.model.Copy;
import com.example.library.model.Lease;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LeaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final BookRepository bookRepository;
    private final UserService userService;

    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    public Optional<Lease> getLeaseById(long id) {
        return leaseRepository.findById(id);
    }

    public List<Lease> getLeasesByUser(User user) {
        return leaseRepository.findByUser(user);
    }

    public List<Lease> getLeasesByBook(Book book) { return leaseRepository.findByBook(book.getId()); }

    public List<Lease> getLeasesByCopy(Copy copy) {
        return leaseRepository.findByCopy(copy);
    }

    public List<Lease> getLeasesByCopyId(long id) {
        return leaseRepository.findByCopyId(id);
    }

    public Lease createLease(Lease lease) {
        return leaseRepository.save(lease);
    }

    public Lease updateLease(Lease lease) {
        //todo
        return null;
    }

    public void deleteLease(long id) {
        bookRepository.deleteById(id);
    }
}
