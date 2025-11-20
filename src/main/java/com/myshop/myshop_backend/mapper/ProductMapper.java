package com.myshop.myshop_backend.mapper;

import com.myshop.myshop_backend.dto.ProductDto;
import com.myshop.myshop_backend.entity.Category;
import com.myshop.myshop_backend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product entity);

    @Mapping(target = "category", expression = "java(mapCategory(dto.getCategoryId()))")
    Product toEntity(ProductDto dto);

    default Category mapCategory(UUID id) {
        if (id == null) return null;
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
