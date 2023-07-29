package com.back.end.model;

import com.back.end.dto.CategoryDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public static Category convert(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(category.getId());
        category.setName(category.getName());
        return category;
    }
}
