package com.example.RESTapi;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Dish;
import com.example.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
/*
Контроллер Dish
все работает, проверила на постмене
пофиксила 7.10, вылетает бд при изменени значений в product_dish
 */
@RestController
@RequestMapping(path = "dish")
public class DishController {
    @Autowired
    private DishRepository dishRepository;

    @GetMapping()
    public @ResponseBody
    Iterable<Dish> getAllDish() {
        return dishRepository.findAll();
    }

    @GetMapping("{dishID}")
    public ResponseEntity<Dish> getDishById(@PathVariable(value = "dishID") int dishID)
            throws ResourceNotFoundException {
        Dish dish = dishRepository.findById(dishID)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found for this id :: " + dishID));
        return ResponseEntity.ok().body(dish);
    }

    @PostMapping()
    public Dish createDish(@Valid @RequestBody Dish dish){
        return dishRepository.save(dish);
    }

    @PutMapping("{dishID}")
    public ResponseEntity<Dish> updateDish(@PathVariable(value = "dishID") int dishID,
                                              @Valid @RequestBody Dish dishDetails) throws ResourceNotFoundException {
        Dish dish = dishRepository.findById(dishID)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found for this id :: " + dishID));


        dish.setDishName(dishDetails.getDishName());
        dish.setPrice(dishDetails.getPrice());

        final Dish updatedDish = dishRepository.save(dish);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("{dishid}")
    public Map<String, Boolean> deleteDish(@PathVariable(value = "dishID") int dishID)
            throws ResourceNotFoundException {
        Dish dish = dishRepository.findById(dishID)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found for this id :: " + dishID));

        dishRepository.delete(dish);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
