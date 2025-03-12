package com.example.library.controller;


import com.example.library.dto.CreateLeaseRequest;
import com.example.library.model.Book;
import com.example.library.model.Lease;
import com.example.library.model.User;
import com.example.library.service.LeaseService;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leases")
public class LeaseController {
    private  final LeaseService leaseService;
    private  final UserService userService;

    @GetMapping
    public List<Lease> getAllLeases() {
        return leaseService.getAllLeases();
    }

    @GetMapping("/{id}")
    public Lease getLeaseById(@PathVariable Long id) {
        Optional<Lease> lease = leaseService.getLeaseById(id);
        return lease.orElse(null);
    }

    @GetMapping("/copy")
    public List<Lease> getLeaseByCopyId(@PathVariable Long id) {
        return leaseService.getLeasesByCopyId(id);
    }

    @GetMapping("/users/{id}")
    public List<Lease> getLeaseByUser(@PathVariable Long id) {
        List<Lease> leases = leaseService.getLeasesByUser(id);
        return leases;
    }

    @GetMapping("/book")
    public List<Lease> getLeasesByBook(Book book) {
        return leaseService.getLeasesByBook(book);
    }

    @PostMapping("/create")
    public Lease createLease(@RequestBody CreateLeaseRequest request) {
        return leaseService.saveLease(request);
    }


    @DeleteMapping("/{id}")
    public void deleteLease(@PathVariable Long id) {
        leaseService.deleteLeaseById(id);
    }


}
