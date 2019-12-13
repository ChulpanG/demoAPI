package com.example.RESTapi;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Orders;
import com.example.repository.OrderRepository;
import com.example.repository.PersonRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
/*
Контроллер Orders, все таблицы подключены, все работает
 */
@RestController
@RequestMapping(path = "orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping()
    public @ResponseBody Iterable<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("{orderID}")
    public ResponseEntity<Orders> getOrderById(@PathVariable(value = "orderID") int orderID)
            throws ResourceNotFoundException {
        Orders orders = orderRepository.findById(orderID)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderID));
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping()
    public Orders createOrder(@Valid @RequestBody Orders orders){
        return orderRepository.save(orders);
    }

    @PutMapping("{orderID}")

    public ResponseEntity<Orders> updateOrder(@PathVariable(value = "orderID") int orderID,
                                               @Valid @RequestBody Orders orderDetails) throws ResourceNotFoundException {
        Orders orders = orderRepository.findById(orderID)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderID));

        orders.setPersonID(orderDetails.getPersonID());
        orders.setTime(orderDetails.getTime());

        final Orders updatedOrders = orderRepository.save(orders);
        return ResponseEntity.ok(updatedOrders);
    }

    @DeleteMapping("{orderID}")
    public Map<String, Boolean> deleteOrders(@PathVariable(value = "orderID") int orderID)
            throws ResourceNotFoundException {
        Orders orders = orderRepository.findById(orderID)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderID));

        orderRepository.delete(orders);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
