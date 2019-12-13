package com.example.RESTapi;

import com.example.exception.ResourceNotFoundException;
import com.example.model.ProductDish;
import com.example.repository.ProductDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
/*
Контроллер промежуточной таблицы product_dish
не работает пут, дел сделан с костылем
по возможности переделать
 */
@RestController
@RequestMapping(path = "productdish")
public class ProductDishController {
    @Autowired
    private ProductDishRepository productDishRepository;
    //гет рабочий
    @GetMapping()
    public @ResponseBody Iterable<ProductDish> getAllProductDish() {
        return productDishRepository.findAll();
    }
    //рабочий пост, но без поиска, опять же нет проверки на уже сущетсвующие поля, но вроде как все итак работает
    @PostMapping("{dishid}")
    public ResponseEntity<ProductDish> createProductDish(@PathVariable(value = "dishid") int dishID,
                                                         @Valid @RequestBody ProductDish productDishDetails){
        ProductDish productdish = new ProductDish();
        productdish.setDishID(dishID);
        productdish.setProductID(productDishDetails.getProductID());
        final ProductDish updatedProduct = productDishRepository.save(productdish);
        return ResponseEntity.ok(updatedProduct);
    }
    //не работает поиск через репозиторий, также надо решить проблему, до этого момента запрос пут работать не будет
    @PutMapping("{dishid}")
    public ResponseEntity<ProductDish> updateProductDish(@PathVariable(value = "dishid") int productDishID,
                                           @Valid @RequestBody ProductDish productDishDetails) throws ResourceNotFoundException {
        ProductDish productDish = productDishRepository.findById(productDishID)
                .orElseThrow(() -> new ResourceNotFoundException("ProductDish not found for this id :: " + productDishID));

        productDish.setDishID(productDishDetails.getDishID());
        productDish.setProductID(productDishDetails.getProductID());

        final ProductDish updatedProductDish = productDishRepository.save(productDish);
        return ResponseEntity.ok(updatedProductDish);
    }
    //здесь косяк с определение экспешона, не могу придумать как сделать поиск по двум первичным ключам в таблице
    @DeleteMapping("{dishid}/{productid}")
    public Map<String, Boolean> deleteProductDish(@PathVariable(value = "dishid") int dishID, @PathVariable(value = "productid") int productID)
            throws ResourceNotFoundException {

        ProductDish productDish = new ProductDish();
        productDish.setDishID(dishID);
        productDish.setProductID(productID);
        productDishRepository.delete(productDish);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
