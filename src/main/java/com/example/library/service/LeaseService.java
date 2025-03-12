package com.example.library.service;


import com.example.library.dto.CreateLeaseRequest;
import com.example.library.mapper.DtoMapper;
import com.example.library.model.Book;
import com.example.library.model.Copy;
import com.example.library.model.Lease;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CopyRepository;
import com.example.library.repository.LeaseRepository;
import com.example.library.repository.UserRepository;
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
    private final CopyRepository copyRepository;
    private final UserRepository userRepository;

    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    public Optional<Lease> getLeaseById(long id) {
        return leaseRepository.findById(id);
    }

    public List<Lease> getLeasesByUser(Long id) {
        return leaseRepository.findByUser(id);
    }

    public List<Lease> getLeasesByBook(Book book) { return leaseRepository.findByBook(book.getId()); }

    public List<Lease> getLeasesByCopy(Copy copy) {
        return leaseRepository.findByCopy(copy);
    }

    public List<Lease> getLeasesByCopyId(long id) {
        return leaseRepository.findByCopyId(id);
    }

    public Lease saveLease(CreateLeaseRequest request) {
        User user = userRepository.findById(request.getUser()).orElse(null);
        Copy copy = copyRepository.findById(request.getCopy()).orElse(null);
        Lease savedLease = DtoMapper.createLeaseRequestToLease(request, user, copy);
        return leaseRepository.save(savedLease);
    }

    public Lease updateLease(Lease lease) {
        //todo
        return null;
    }

    public void deleteLeaseById(long id) {
        Optional<Lease> optionalLease = leaseRepository.findById(id);
        Lease lease = optionalLease.orElse(null);
        leaseRepository.delete(lease);
    }
}
