package com.example.RESTapi;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
/*
Контроллер Product
ФИКС от 7.10
все работает как надо, все подключено
 */
@RestController
@RequestMapping(path = "product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public @ResponseBody
    Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("{productid}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "productid") int productid)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productid)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productid));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping()
    public Product createProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }
    
    @PutMapping("{productid}")
    public Product update(
            @PathVariable("productid") Product productFromDb,
            @RequestBody Product product
    ) {
        BeanUtils.copyProperties(product, productFromDb, "productid");

        return productRepository.save(productFromDb);
    }

    @DeleteMapping("{productid}")
    /*public void delete(@PathVariable("productid") Product product) {
        productRepository.delete(product);*/

    public Map<String, Boolean> deleteProduct(@PathVariable(value = "productid") int productid)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productid)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productid));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
