package com.example.narnia.pharmacy.controller;

import com.example.narnia.pharmacy.model.Sale;
import com.example.narnia.pharmacy.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private SaleService saleService;

    // Endpoint pour récupérer les statistiques des rapports
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getReportStats() {
        // Statistiques dynamiques
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalReports", 24);  // Exemple statique, peut être remplacé par une logique dynamique
        stats.put("growth", "+15%");    // Exemple statique
        stats.put("insights", 12);      // Exemple statique

        return ResponseEntity.ok(stats);
    }

    // Endpoint pour récupérer les données de ventes regroupées par mois (pour un graphique)
    @GetMapping("/sales")
    public ResponseEntity<List<Map<String, Object>>> getSalesData() {
        List<Sale> sales = saleService.getAllSales();

        // Grouper les ventes par mois et sommer le total
        Map<Month, Double> salesByMonth = sales.stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getDate().getMonth(),
                        Collectors.summingDouble(Sale::getTotal)
                ));

        // Convertir la map en liste de maps pour le format de réponse
        List<Map<String, Object>> salesData = new ArrayList<>();
        for (Map.Entry<Month, Double> entry : salesByMonth.entrySet()) {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("name", entry.getKey().name());
            monthData.put("sales", entry.getValue());
            salesData.add(monthData);
        }

        // Optionnel : trier par ordre chronologique (selon la valeur numérique du mois)
        salesData.sort(Comparator.comparing(m -> Month.valueOf((String) m.get("name")).getValue()));

        return ResponseEntity.ok(salesData);
    }
}
