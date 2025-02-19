package com.example.narnia.pharmacy.controller;

import com.example.narnia.pharmacy.model.Customer;
import com.example.narnia.pharmacy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Endpoint pour récupérer les statistiques des clients
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getCustomerStats() {
        List<Customer> customers = customerService.getAllCustomers();
        long totalCustomers = customers.size();
        long activeCustomers = customers.stream()
                .filter(c -> "Active".equalsIgnoreCase(c.getStatus()))
                .count();
        long loyaltyMembers = customers.stream()
                .filter(c -> "Loyal".equalsIgnoreCase(c.getStatus()))
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCustomers", totalCustomers);
        stats.put("activeCustomers", activeCustomers);
        stats.put("loyaltyMembers", loyaltyMembers);

        return ResponseEntity.ok(stats);
    }

    // Endpoint pour récupérer la liste des clients récents
    @GetMapping("/recent")
    public ResponseEntity<List<Customer>> getRecentCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<Customer> recentCustomers = customers.stream()
                .sorted(Comparator.comparing(Customer::getLastVisit).reversed())
                .limit(10)  // Limiter aux 10 clients les plus récents par exemple
                .collect(Collectors.toList());
        return ResponseEntity.ok(recentCustomers);
    }

    // Endpoint pour récupérer un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
