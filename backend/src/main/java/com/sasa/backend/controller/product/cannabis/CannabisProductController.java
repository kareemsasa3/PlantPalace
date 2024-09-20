package com.sasa.backend.controller.product.cannabis;

import com.sasa.backend.dto.product.cannabis.CannabisProductDTO;
import com.sasa.backend.service.product.cannabis.CannabisProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Cannabis Products.
 */
@RestController
@RequestMapping("/api/products/cannabis")
public class CannabisProductController {

    private final CannabisProductService cannabisProductService;

    public CannabisProductController(CannabisProductService cannabisProductService) {
        this.cannabisProductService = cannabisProductService;
    }

    /**
     * Get all cannabis products.
     *
     * @return List of CannabisProductDTO
     */
    @GetMapping
    public ResponseEntity<List<CannabisProductDTO>> getAllCannabisProducts() {
        List<CannabisProductDTO> products = cannabisProductService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get cannabis products by category.
     *
     * @param category the category to filter by
     * @return List of CannabisProductDTO
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<CannabisProductDTO>> getCannabisProductsByCategory(@PathVariable String category) {
        List<CannabisProductDTO> products = cannabisProductService.findByCannabisCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get cannabis products by type.
     *
     * @param type the type to filter by
     * @return List of CannabisProductDTO
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<CannabisProductDTO>> getCannabisProductsByType(@PathVariable String type) {
        List<CannabisProductDTO> products = cannabisProductService.findByCannabisType(type);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get cannabis product by name.
     *
     * @param name the name of the product
     * @return CannabisProductDTO
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<CannabisProductDTO> getCannabisProductByName(@PathVariable String name) {
        Optional<CannabisProductDTO> product = cannabisProductService.findByName(name);
        return product.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create or update a cannabis product.
     *
     * @param cannabisProductDTO the product to save
     * @return CannabisProductDTO
     */
    @PostMapping
    public ResponseEntity<CannabisProductDTO> createOrUpdateCannabisProduct(@RequestBody CannabisProductDTO cannabisProductDTO) {
        CannabisProductDTO savedProduct = cannabisProductService.save(cannabisProductDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<CannabisProductDTO>> createCannabisProducts(@RequestBody List<CannabisProductDTO> cannabisProductDTOs) {
        List<CannabisProductDTO> savedProducts = cannabisProductService.saveAll(cannabisProductDTOs);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

    /**
     * Delete a cannabis product by ID.
     *
     * @param id the ID of the product to delete
     * @return HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCannabisProduct(@PathVariable Long id) {
        cannabisProductService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
