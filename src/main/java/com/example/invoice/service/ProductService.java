package com.example.invoice.service;

import com.example.invoice.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    Optional<ProductDTO> getProductById(Long id);
    Page<ProductDTO> getAllProducts(Pageable pageable);
    void deleteProduct(Long id);

    // ✅ Add this method
    ProductDTO saveProduct(ProductDTO productDTO);
}
