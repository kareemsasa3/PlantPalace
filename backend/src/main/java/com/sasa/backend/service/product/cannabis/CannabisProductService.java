package com.sasa.backend.service.product.cannabis;

import com.sasa.backend.dto.product.cannabis.CannabisProductDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing CannabisProduct entities.
 */
public interface CannabisProductService {

    /**
     * Finds all CannabisProduct entities.
     *
     * @return a list of CannabisProductDTOs.
     */
    List<CannabisProductDTO> findAll();

    /**
     * Finds CannabisProduct entities by their cannabis category.
     *
     * @param cannabisCategory the cannabis category.
     * @return a list of CannabisProductDTOs.
     */
    List<CannabisProductDTO> findByCannabisCategory(String cannabisCategory);

    /**
     * Finds CannabisProduct entities by their cannabis type.
     *
     * @param cannabisType the cannabis type.
     * @return a list of CannabisProductDTOs.
     */
    List<CannabisProductDTO> findByCannabisType(String cannabisType);

    /**
     * Finds a CannabisProduct entity by its name.
     *
     * @param name the name of the cannabis product.
     * @return an Optional containing the CannabisProductDTO if found, or an empty Optional if not found.
     */
    Optional<CannabisProductDTO> findByName(String name);

    /**
     * Saves a CannabisProduct entity.
     *
     * @param cannabisProductDTO the CannabisProductDTO to be saved.
     * @return the saved CannabisProductDTO.
     */
    CannabisProductDTO save(CannabisProductDTO cannabisProductDTO);

    /**
     * Saves a list of CannabisProduct entities.
     * 
     * @param cannabisProductDTOs the CannabisProductDTOs to be saved.
     * @return the saved CannabisProductDTOs
     */
    List<CannabisProductDTO> saveAll(List<CannabisProductDTO> cannabisProductDTOs);

    /**
     * Deletes a CannabisProduct entity by its ID.
     *
     * @param id the ID of the CannabisProduct entity to be deleted.
     */
    void deleteById(Long id);
}