package com.example.repository;


import com.example.model.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDishPKRepository extends JpaRepository<OrderDish, Integer> {
   //OrderDishPK findById(int orderID, int dishID);
}


