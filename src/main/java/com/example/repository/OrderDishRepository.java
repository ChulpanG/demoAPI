package com.example.repository;

import com.example.model.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {

}
