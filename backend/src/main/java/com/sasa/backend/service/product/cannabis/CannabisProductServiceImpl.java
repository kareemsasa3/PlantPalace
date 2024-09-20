package com.sasa.backend.service.product.cannabis;

import com.sasa.backend.dto.product.cannabis.CannabisProductDTO;
import com.sasa.backend.entity.product.cannabis.CannabisProduct;
import com.sasa.backend.repository.product.cannabis.CannabisProductRepository;
import com.sasa.backend.util.mapper.product.cannabis.CannabisProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of CannabisProductService interface.
 */
@Service
public class CannabisProductServiceImpl implements CannabisProductService {

    private final CannabisProductRepository cannabisProductRepository;

    public CannabisProductServiceImpl(CannabisProductRepository cannabisProductRepository) {
        this.cannabisProductRepository = cannabisProductRepository;
    }

    @Override
    public List<CannabisProductDTO> findAll() {
        List<CannabisProduct> products = cannabisProductRepository.findAll();
        return products.stream()
                .map(CannabisProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CannabisProductDTO> findByCannabisCategory(String cannabisCategory) {
        List<CannabisProduct> products = cannabisProductRepository.findByCategory(cannabisCategory);
        return products.stream()
                .map(CannabisProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CannabisProductDTO> findByCannabisType(String cannabisType) {
        List<CannabisProduct> products = cannabisProductRepository.findByType(cannabisType);
        return products.stream()
                .map(CannabisProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CannabisProductDTO> findByName(String name) {
        Optional<CannabisProduct> product = cannabisProductRepository.findByName(name);
        return product.map(CannabisProductMapper::toDTO);
    }

    @Override
    public CannabisProductDTO save(CannabisProductDTO cannabisProductDTO) {
        CannabisProduct cannabisProduct = CannabisProductMapper.toProduct(cannabisProductDTO);
        CannabisProduct savedProduct = cannabisProductRepository.save(cannabisProduct);
        return CannabisProductMapper.toDTO(savedProduct);
    }

    @Override
    public List<CannabisProductDTO> saveAll(List<CannabisProductDTO> cannabisProductDTOs) {
        List<CannabisProduct> cannabisProducts = CannabisProductMapper.toProductList(cannabisProductDTOs);
        List<CannabisProduct> savedProducts = cannabisProductRepository.saveAll(cannabisProducts);
        return CannabisProductMapper.toDTOList(savedProducts);
    }

    @Override
    public void deleteById(Long id) {
        cannabisProductRepository.deleteById(id);
    }
}