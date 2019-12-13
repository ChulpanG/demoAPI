package com.example.repository;

import com.example.model.ProductDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDishRepository extends JpaRepository<ProductDish, Integer> {
}
