package com.sasa.backend.controller;

import com.sasa.backend.dto.product.CannabisProductDTO;
import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Create a new Cannabis Product
    @PostMapping("/cannabis")
    public ResponseEntity<CannabisProductDTO> createCannabisProduct(@RequestBody CannabisProductDTO cannabisProductDTO) {
        CannabisProductDTO createdProduct = productService.createCannabisProduct(cannabisProductDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/cannabis")
    public ResponseEntity<List<CannabisProductDTO>> getAllCannabisProducts() {
        List<CannabisProductDTO> cannabisProducts = productService.getAllCannabisProducts();
        return ResponseEntity.ok(cannabisProducts);
    }

    @PostMapping("/cannabis/bulk")
    public ResponseEntity<List<CannabisProductDTO>> createCannabisProducts(@RequestBody List<CannabisProductDTO> cannabisProductDTOs) {
        List<CannabisProductDTO> createdCannabisProducts = productService.createCannabisProducts(cannabisProductDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCannabisProducts);
    }
}