package com.sasa.backend.repository.product.cannabis;

import com.sasa.backend.entity.product.cannabis.CannabisProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on CannabisProduct entities.
 */
@Repository
public interface CannabisProductRepository extends JpaRepository<CannabisProduct, Long> {

    /**
     * Finds all CannabisProduct entities by their cannabis category.
     *
     * @param cannabisCategory the cannabis category.
     * @return a list of CannabisProduct entities.
     */
    List<CannabisProduct> findByCategory(String cannabisCategory);

    /**
     * Finds all CannabisProduct entities by their cannabis type.
     *
     * @param cannabisType the cannabis type.
     * @return a list of CannabisProduct entities.
     */
    List<CannabisProduct> findByType(String cannabisType);

    /**
     * Finds a CannabisProduct entity by its name.
     *
     * @param name the name of the cannabis product.
     * @return an Optional containing the CannabisProduct entity if found, or an empty Optional if not found.
     */
    Optional<CannabisProduct> findByName(String name);
}