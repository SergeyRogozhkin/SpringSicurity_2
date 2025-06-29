package org.example.springsicurity2hw_2.controller;

import org.example.springsicurity2hw_2.dto.RequestResponse;
import org.example.springsicurity2hw_2.entity.Product;
import org.example.springsicurity2hw_2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/public/product")
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/admin/saveproduct")
    public ResponseEntity<Object> signUp(@RequestBody RequestResponse product) {
       Product productToSave = new Product();
       productToSave.setName(product.getName());
        return ResponseEntity.ok(productRepository.save(productToSave));
    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone() {
        return ResponseEntity.ok("userAlone");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminUser() {
        return ResponseEntity.ok("bothAdminUser");
    }

}
