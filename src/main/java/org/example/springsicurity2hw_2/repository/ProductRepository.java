package org.example.springsicurity2hw_2.repository;

import org.example.springsicurity2hw_2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
