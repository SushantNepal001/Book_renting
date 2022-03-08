package com.rastapi.brs.controller;

import com.rastapi.brs.Dto.CategoryDto;
import com.rastapi.brs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String categoryMainPage(Model model) {
//        model.addAttribute("categoryDto",new CategoryDto());
        model.addAttribute("categoryDtoList", categoryService.findAllCategoryList());
        return "category/category";
    }

    @GetMapping("/addCategory")
    public String openPage(Model model) {
        if (model.getAttribute("categoryDto") == null)
            model.addAttribute("categoryDto", new CategoryDto());
            return "category/addCategory";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute CategoryDto categoryDto, RedirectAttributes redirectAttributes) throws IOException {
        String message = "";
        categoryDto = categoryService.saveCategory(categoryDto);
        if (categoryDto == null) {
            message = "category cannot be saved";
        } else {
            message = categoryDto.getId() != null ? "Category Saved successfully" : "Category updated successfully!";
        }
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/category/addCategory";
    }

    @GetMapping("/find-by-id/{id}")
    public String findbyId(@PathVariable("id") Integer categoryId, RedirectAttributes redirectAttributes) {
        CategoryDto categoryDto = categoryService.findById(categoryId);
        if (categoryDto != null)
            redirectAttributes.addFlashAttribute("categoryDto", categoryDto);
        return "redirect:/category/addCategory";
    }

    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/category";
    }

}
