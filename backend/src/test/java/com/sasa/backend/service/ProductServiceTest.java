package com.sasa.backend.service;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.Category;
import com.sasa.backend.entity.Type;
import com.sasa.backend.entity.product.Product;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.repository.ProductRepository;
import com.sasa.backend.util.mapper.ProductMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService; // Using the concrete implementation class

    @BeforeEach
    void setUp() {
        // Initialization, if necessary
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, Category.FLOWER, "Product1", "Brand1", Type.INDICA, 10.0, 5.0, 50.0, 100, "image1", "description1", "effects1", Map.of("Terpene1", 0.5));
        Product product2 = new Product(2L, Category.CONCENTRATES, "Product2", "Brand2", Type.HYBRID, 15.0, 10.0, 75.0, 200, "image2", "description2", "effects2", Map.of("Terpene2", 0.7));
        ProductDTO dto1 = ProductMapper.toDTO(product1);
        ProductDTO dto2 = ProductMapper.toDTO(product2);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<ProductDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product product = new Product(productId, Category.EDIBLES, "Product1", "Brand1", Type.SATIVA, 10.0, 5.0, 50.0, 100, "image1", "description1", "effects1", Map.of("Terpene1", 0.5));
        ProductDTO expectedDTO = ProductMapper.toDTO(product);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<ProductDTO> result = productService.getProductById(productId);

        assertTrue(result.isPresent(), "Expected ProductDTO to be present");
        assertEquals(expectedDTO, result.get(), "ProductDTO does not match expected value");
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
        assertEquals("Product not found with id: 1", thrown.getMessage());
    }

    @Test
    void testSaveProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product1");
        productDTO.setCategory("TINCTURES");
        productDTO.setBrand("Brand1");
        productDTO.setType("SATIVA_HYBRID");
        productDTO.setThc(10.0);
        productDTO.setAmount(5.0);
        productDTO.setPrice(50.0);
        productDTO.setQuantity(100);
        productDTO.setImage("image1");
        productDTO.setDescription("description1");
        productDTO.setEffects("effects1");
        productDTO.setTerpenes(Map.of("Terpene1", 0.5));

        Product product = ProductMapper.toEntity(productDTO);
        Product savedProduct = new Product(1L, Category.TINCTURES, "Product1", "Brand1", Type.SATIVA_HYBRID, 10.0, 5.0, 50.0, 100, "image1", "description1", "effects1", Map.of("Terpene1", 0.5));
        ProductDTO savedDTO = ProductMapper.toDTO(savedProduct);

        when(productRepository.save(product)).thenReturn(savedProduct);

        ProductDTO result = productService.saveProduct(productDTO);

        assertNotNull(result);
        assertEquals(savedDTO.getName(), result.getName());
        assertEquals(savedDTO.getCategory(), result.getCategory());
        assertEquals(savedDTO.getBrand(), result.getBrand());
        assertEquals(savedDTO.getType(), result.getType());
        assertEquals(savedDTO.getThc(), result.getThc());
        assertEquals(savedDTO.getAmount(), result.getAmount());
        assertEquals(savedDTO.getPrice(), result.getPrice());
        assertEquals(savedDTO.getQuantity(), result.getQuantity());
        assertEquals(savedDTO.getImage(), result.getImage());
        assertEquals(savedDTO.getDescription(), result.getDescription());
        assertEquals(savedDTO.getEffects(), result.getEffects());
        assertEquals(savedDTO.getTerpenes(), result.getTerpenes());
    }


    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("UpdatedProduct");
        productDTO.setBrand("MrTasteDeez");
        productDTO.setCategory("FLOWER");
        productDTO.setType("SATIVA");
        productDTO.setThc(34.5);
        productDTO.setAmount(3.5);
        productDTO.setPrice(30.00);
        productDTO.setQuantity(1);
        productDTO.setImage(null);
        productDTO.setDescription(null);
        productDTO.setEffects(null);
        productDTO.setTerpenes(null);

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setTerpenes(new HashMap<>());
        
        Product savedProduct = new Product(productId, Category.FLOWER, "UpdatedProduct", "OldBrand", Type.INDICA, 10.0, 5.0, 50.0, 100, "image1", "description1", "effects1", Collections.emptyMap());
        ProductDTO savedDTO = ProductMapper.toDTO(savedProduct);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductDTO result = productService.updateProduct(productId, productDTO);

        assertNotNull(result);
        assertEquals(savedDTO.getName(), result.getName());
    }

    @Test
    void testUpdateProduct_NotFound() {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(productId, productDTO));
        assertEquals("Product not found with id: 1", thrown.getMessage());
    }

    @Test
    void testGetProductsByCategory() {
        Category category = Category.FLOWER;
        Product product1 = new Product(1L, category, "Product1", "Brand1", Type.INDICA, 10.0, 5.0, 50.0, 100, "image1", "description1", "effects1", Collections.emptyMap());
        ProductDTO dto1 = ProductMapper.toDTO(product1);

        when(productRepository.findByCategory(category)).thenReturn(Arrays.asList(product1));

        List<ProductDTO> result = productService.getProductsByCategory(category);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto1, result.get(0));
    }

    @Test
    void testGetProductsByType() {
        Type type = Type.INDICA;
        Product product1 = new Product(1L, Category.FLOWER, "Product1", "Brand1", type, 10.0, 5.0, 50.0, 100, "image1", "description1", "effects1", Collections.emptyMap());
        ProductDTO dto1 = ProductMapper.toDTO(product1);

        when(productRepository.findByType(type)).thenReturn(Arrays.asList(product1));

        List<ProductDTO> result = productService.getProductsByType(type);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto1, result.get(0));
    }

    @Test
    void testDeleteProduct() {
        Long id = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        doNothing().when(productRepository).delete(existingProduct);

        productService.deleteProduct(id);

        verify(productRepository, times(1)).delete(existingProduct);
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1L));
        assertEquals("Product not found with id: 1", thrown.getMessage());
    }
}
