package com.example.ecommerceapp.controllers;

import com.example.ecommerceapp.entities.Product;
import com.example.ecommerceapp.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("api/products")
    List<Product> findAllCategories() {
        return (List<Product>) productService.findAllProducts();
    }

    @GetMapping("/api/products/{id}")
    public Product findById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PostMapping("/api/products")
    public Product save(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("api/product/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Optional<Product> productFromDatabase = Optional.ofNullable(productService.findById(id));
        if (productFromDatabase.isPresent()) {
            productFromDatabase.get().setSku(product.getSku());
            productFromDatabase.get().setName(product.getName());
            productFromDatabase.get().setDescription(product.getDescription());
            productFromDatabase.get().setUnitPrice(product.getUnitPrice());
            productFromDatabase.get().setImageUrl(product.getImageUrl());
            productFromDatabase.get().setActive(product.getActive());
            productFromDatabase.get().setUnitsInStock(product.getUnitsInStock());
            productFromDatabase.get().setDateCreated(product.getDateCreated());
            productFromDatabase.get().setLastUpdated(product.getLastUpdated());
            final Product updatedProduct = productService.saveProduct(productFromDatabase.get());
            return updatedProduct;
        }
        return productService.update(product);
    }

    @DeleteMapping("api/products/{id}")
    public void deleteProductById(@PathVariable("id") long id){
        productService.deleteProduct(id);
    }
}