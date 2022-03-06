package com.rastapi.brs.service.impl;

import com.rastapi.brs.Dto.CategoryDto;
import com.rastapi.brs.entities.Category;
import com.rastapi.brs.repo.CategoryRepo;
import com.rastapi.brs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category entity=new Category().builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
        entity=categoryRepo.save(entity);

        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public List<CategoryDto> findAllCategoryList() {
        List<Category> categoryList=categoryRepo.findAll();
        return  categoryList.stream().map(entity->CategoryDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .build()).collect(Collectors.toList());

    }

    @Override
    public CategoryDto findById(Integer id) {
        Category c;
        Optional<Category> optionalCategory=categoryRepo.findById(id);
        if(optionalCategory.isPresent()){
            c=optionalCategory.get();
            return CategoryDto.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .description(c.getDescription())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepo.deleteById(id);

    }
}
