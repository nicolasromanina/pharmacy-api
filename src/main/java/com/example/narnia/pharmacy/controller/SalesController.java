package com.example.narnia.pharmacy.controller;

import com.example.narnia.pharmacy.model.Sale;
import com.example.narnia.pharmacy.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SaleService saleService;

    // Endpoint pour récupérer les statistiques de ventes
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getSalesStats() {
        List<Sale> sales = saleService.getAllSales();
        double totalSales = sales.stream().mapToDouble(Sale::getTotal).sum();
        double todaySales = sales.stream()
                .filter(sale -> sale.getDate().equals(LocalDate.now()))
                .mapToDouble(Sale::getTotal)
                .sum();
        double averageOrder = sales.isEmpty() ? 0 : totalSales / sales.size();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSales", totalSales);
        stats.put("todaySales", todaySales);
        stats.put("averageOrder", averageOrder);

        return ResponseEntity.ok(stats);
    }

    // Endpoint pour récupérer les ventes récentes
    @GetMapping("/recent")
    public ResponseEntity<List<Sale>> getRecentSales() {
        List<Sale> sales = saleService.getAllSales();
        List<Sale> recentSales = sales.stream()
                .sorted(Comparator.comparing(Sale::getDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recentSales);
    }

    // Endpoint pour récupérer une vente par ID
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable String id) {
        Sale sale = saleService.getSaleById(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
