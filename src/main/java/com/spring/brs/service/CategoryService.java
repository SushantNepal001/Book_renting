package com.spring.brs.service;

import com.spring.brs.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    List<CategoryDto> findAllCategoryList();
    CategoryDto findById(Integer id);
    void deleteCategoryById(Integer id);

}
