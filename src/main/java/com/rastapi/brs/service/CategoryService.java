package com.rastapi.brs.service;

import com.rastapi.brs.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    List<CategoryDto> findAllCategoryList();
    CategoryDto findById(Integer id);
    void deleteCategoryById(Integer id);

}
