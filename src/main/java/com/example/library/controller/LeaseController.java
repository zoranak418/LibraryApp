package com.example.library.controller;


import com.example.library.dto.CreateLeaseRequest;
import com.example.library.dto.LeaseResponse;
import com.example.library.dto.UpdateLeaseRequest;
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
    public List<LeaseResponse> getAllLeases() {
        return leaseService.getAllLeases();
    }

    @GetMapping("/{id}")
    public LeaseResponse getLeaseById(@PathVariable Long id) {
        return leaseService.getLeaseById(id);
    }

    @GetMapping("/copies/{id}")
    public List<LeaseResponse> getLeaseByCopyId(@PathVariable Long id) {
        return leaseService.getLeasesByCopy(id);
    }

    @GetMapping("/users/{id}")
    public List<LeaseResponse> getLeaseByUser(@PathVariable Long id) {
        return leaseService.getLeasesByUser(id);
    }

    @GetMapping("/books/{id}")
    public List<LeaseResponse> getLeasesByBook(@PathVariable Long id) {
        return leaseService.getLeasesByBook(id);
    }

    @PostMapping("/create")
    public LeaseResponse createLease(@RequestBody CreateLeaseRequest request) {
        return leaseService.saveLease(request);
    }

    @PutMapping("/{id}")
    public LeaseResponse updateLease(@PathVariable Long id, @RequestBody UpdateLeaseRequest request) {
        Lease lease = leaseService.getById(id);
        return leaseService.updateLease(request, lease);
    }


    @DeleteMapping("/{id}")
    public void deleteLease(@PathVariable Long id) {
        leaseService.deleteLeaseById(id);
    }


}
