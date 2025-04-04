package com.example.invoice.mapper;

import com.example.invoice.dto.ProductDTO;
import com.example.invoice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // Convert Product entity to ProductDTO
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getId(),
                product.getProductCode(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getTaxRate()
        );
    }

    // Convert ProductDTO to Product entity
    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductCode(productDTO.getProductCode());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setTaxRate(productDTO.getTaxRate());

        return product;
    }
}
