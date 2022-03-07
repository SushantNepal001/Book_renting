package com.rastapi.brs.controller;

import com.rastapi.brs.Dto.BookDto;
import com.rastapi.brs.service.AuthorService;
import com.rastapi.brs.service.BookService;
import com.rastapi.brs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    public BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String bookMainPage(Model model) {
        model.addAttribute("bookDtoList", bookService.findAllBook());
        return "book/book";

    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        if (model.getAttribute("bookDto") == null)
            model.addAttribute("bookDto", new BookDto());
            model.addAttribute("categories",categoryService.findAllCategoryList());
            model.addAttribute("authors",authorService.findAll());
        return "book/addBook";
    }

    @PostMapping
    public String saveBook(@ModelAttribute BookDto bookDto, RedirectAttributes redirectAttributes) throws IOException, ParseException {
        String message = "";
        bookDto = bookService.saveBook(bookDto);
        if (bookDto == null) {
            message = "Book cannot be saved";
        } else {
            message = bookDto.getId() != null ? "Book updated sucessfully!" : "book saved sucessfully!";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/book/addBook";
    }

    @GetMapping("/find-by-id/{id}")
    public String findbyId(@PathVariable("id") Integer bookId, RedirectAttributes redirectAttributes) {
        BookDto bookDto = bookService.findById(bookId);
        if (bookDto != null)
            redirectAttributes.addFlashAttribute("bookDto", bookDto);
        return "redirect:/book/addBook";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/book";
    }


}
