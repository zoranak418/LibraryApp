package com.example.library.service;


import com.example.library.dto.CreateLeaseRequest;
import com.example.library.dto.LeaseResponse;
import com.example.library.dto.UpdateLeaseRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final UserService userService;
    private final CopyRepository copyRepository;
    private final UserRepository userRepository;

    public List<LeaseResponse> getAllLeases() {
        List<Lease> leases = leaseRepository.findAll();
        List<LeaseResponse> responses = new ArrayList<LeaseResponse>();
        for (Lease lease : leases) {
            responses.add(DtoMapper.createLeaseToLeaseResponse(lease));
        }
        return responses;
    }

    public Lease getById(Long id) {
        return leaseRepository.findById(id).orElse(null);
    }

    public LeaseResponse getLeaseById(Long id) {
        Lease lease = leaseRepository.findById(id).orElse(null);
        return DtoMapper.createLeaseToLeaseResponse(lease);
    }

    public List<LeaseResponse> getLeasesByUser(Long id) {
        List<Lease> leases = leaseRepository.findByUser(id);
        List<LeaseResponse> responses = new ArrayList<>();
        for (Lease lease : leases) {
            responses.add(DtoMapper.createLeaseToLeaseResponse(lease));
        }
        return responses;
    }

    public List<LeaseResponse> getLeasesByBook(Long id) {
        List<Lease> leases = leaseRepository.findByBook(id);
        List<LeaseResponse> responses = new ArrayList<LeaseResponse>();
        for (Lease lease : leases) {
            responses.add(DtoMapper.createLeaseToLeaseResponse(lease));
        }
        return responses;
    }

    public List<LeaseResponse> getLeasesByCopy(Long id) {
        Copy copy = copyRepository.findById(id).orElse(null);
        List<Lease> leases = leaseRepository.findByCopy(copy);
        List<LeaseResponse> responses = new ArrayList<LeaseResponse>();
        for (Lease lease : leases) {
            responses.add(DtoMapper.createLeaseToLeaseResponse(lease));
        }
        return responses;
    }

    public LeaseResponse saveLease(CreateLeaseRequest request) {
        User user = userRepository.findById(request.getUser()).orElse(null);
        Copy copy = copyRepository.findById(request.getCopy()).orElse(null);
        Long available = bookService.howManyAvailableLong(copy.getBook().getId().longValue());
        if(available <= 0) {
            return null;
            //todo exceptions
        }
        Lease savedLease = DtoMapper.createLeaseRequestToLease(request, user, copy);
        return DtoMapper.createLeaseToLeaseResponse(leaseRepository.save(savedLease));
    }

    public LeaseResponse updateLease(UpdateLeaseRequest updateLeaseRequest, Lease lease) {
        if(updateLeaseRequest.getToDate() != null) {
            lease.setToDate(updateLeaseRequest.getToDate());
        }
        if(updateLeaseRequest.getComments() != null) {
            lease.setComments(updateLeaseRequest.getComments());
        }
        return DtoMapper.createLeaseToLeaseResponse(leaseRepository.save(lease));
    }

    public void deleteLeaseById(long id) {
        Optional<Lease> optionalLease = leaseRepository.findById(id);
        Lease lease = optionalLease.orElse(null);
        leaseRepository.delete(lease);
    }
}
