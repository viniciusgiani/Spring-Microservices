package com.back.end.model;

import com.back.end.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productIdentifier;

    private String name;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(product.getPrice());
        product.setProductIdentifier(productDTO.getProductIdentifier());
        if (productDTO.getCategoryDTO() != null) {
            product.setCategory(Category.convert(productDTO.getCategoryDTO()));
        }
        return product;
    }
}
