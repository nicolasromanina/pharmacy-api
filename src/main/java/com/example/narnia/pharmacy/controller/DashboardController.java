package com.example.narnia.pharmacy.controller;

import com.example.narnia.pharmacy.service.CustomerService;
import com.example.narnia.pharmacy.service.InventoryService;
import com.example.narnia.pharmacy.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;

    // Endpoint pour récupérer les statistiques globales du dashboard
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        long totalInventory = inventoryService.getAllItems().size();
        double totalSales = saleService.getAllSales().stream().mapToDouble(Sale -> Sale.getTotal()).sum();
        long totalCustomers = customerService.getAllCustomers().size();
        long lowStockItems = inventoryService.getAllItems().stream()
                .filter(item -> item.getStock() < 50)
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalInventory", totalInventory);
        stats.put("totalSales", totalSales);
        stats.put("totalCustomers", totalCustomers);
        stats.put("lowStockItems", lowStockItems);

        return ResponseEntity.ok(stats);
    }
}
