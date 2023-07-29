package com.back.end.converter;


import com.back.end.dto.CategoryDTO;
import com.back.end.dto.ProductDTO;
import com.back.end.model.Category;
import com.back.end.model.Product;

public class DTOConverter {

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        category.setId(category.getId());
        category.setName(category.getName());
        return categoryDTO;
    }

    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        product.setName(productDTO.getName());
        product.setPrice(product.getPrice());
        product.setProductIdentifier(product.getProductIdentifier());
        if (product.getCategory() != null) {
            productDTO.setCategoryDTO(DTOConverter.convert(product.getCategory()));
        }
        return productDTO;
    }
}
