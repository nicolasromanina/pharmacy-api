package com.example.narnia.pharmacy.controller;

import com.example.narnia.pharmacy.model.InventoryItem;
import com.example.narnia.pharmacy.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Endpoint pour récupérer les statistiques d'inventaire
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getInventoryStats() {
        List<InventoryItem> items = inventoryService.getAllItems();
        long totalItems = items.size();
        long lowStock = items.stream()
                .filter(item -> item.getStock() < 50) // Seuil fixé à 50 par exemple
                .count();
        long expiringItems = items.stream()
                .filter(item -> item.getExpiry().isBefore(LocalDate.now().plusMonths(3)))
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalItems", totalItems);
        stats.put("lowStock", lowStock);
        stats.put("expiringItems", expiringItems);

        return ResponseEntity.ok(stats);
    }

    // Endpoint pour récupérer la liste des articles d'inventaire
    @GetMapping("/list")
    public ResponseEntity<List<InventoryItem>> getInventoryList() {
        List<InventoryItem> items = inventoryService.getAllItems();
        return ResponseEntity.ok(items);
    }

    // Endpoint pour récupérer un article par ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getItemById(@PathVariable String id) {
        InventoryItem item = inventoryService.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
