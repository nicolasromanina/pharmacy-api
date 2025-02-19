package com.example.narnia.pharmacy.service;

import com.example.narnia.pharmacy.model.Sale;
import com.example.narnia.pharmacy.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(String id) {
        // Use Optional to handle non-existent Sale
        return saleRepository.findById(id).orElse(null);
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Sale updateSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public void deleteSale(String id) {
        saleRepository.deleteById(id);
    }
}
