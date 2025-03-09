package com.example.library.controller;


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
        List<Lease> ls = leaseService.getAllLeases();
        return ls;
    }

    @GetMapping("/{id}")
    public Lease getLeaseById(Long id) {
        Optional<Lease> lease = leaseService.getLeaseById(id);
        return lease.orElse(null);
    }

    @GetMapping("/copy")
    public List<Lease> getLeaseByCopyId(@PathVariable Long id) {
        return leaseService.getLeasesByCopyId(id);
    }

    @GetMapping("/user")
    public List<Lease> getLeaseByUser(@RequestParam User user) {
        List<Lease> leases = leaseService.getLeasesByUser(user);
        return leases;
    }

    @GetMapping("/book")
    public List<Lease> getLeasesByBook(Book book) {
        return leaseService.getLeasesByBook(book);
    }

    @PostMapping
    public Lease createLease(@RequestBody Lease lease) {
        return leaseService.createLease(lease);
    }


    @DeleteMapping("/{id}")
    public void deleteLease(@PathVariable Long id) {
        leaseService.deleteLease(id);
    }


}
