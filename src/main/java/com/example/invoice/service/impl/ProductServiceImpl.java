package com.example.invoice.service.impl;

import com.example.invoice.dto.ProductDTO;
import com.example.invoice.entity.Product;
import com.example.invoice.mapper.ProductMapper;
import com.example.invoice.repository.ProductRepository;
import com.example.invoice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productRepository.existsByProductCode(productDTO.getProductCode())) {
            throw new IllegalArgumentException("Product code already exists.");
        }
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        existingProduct.setProductCode(productDTO.getProductCode());
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setTaxRate(productDTO.getTaxRate());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    // ✅ Added the missing saveProduct method
    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }
}
