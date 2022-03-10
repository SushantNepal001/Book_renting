package com.spring.brs.controller;

import com.spring.brs.Dto.AuthorDto;
import com.spring.brs.service.AuthorService;
import com.spring.brs.utils.EmailSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private EmailSendUtil emailSendUtil;


    @GetMapping("/home")
    public String authorMainPage(Model model) {
        model.addAttribute("authorDtoList", authorService.findAll());
        return "author/home";
    }

    @GetMapping("/addAuthor")
    public String authorFormPage(Model model) {
        if (model.getAttribute("authorDto") == null)
            model.addAttribute("authorDto", new AuthorDto());
        return "author/addAuthor";
    }

    @PostMapping
    public String saveAuthor(@ModelAttribute AuthorDto authorDto, BindingResult bindingResult,
                             RedirectAttributes redirectAttribute, Model model) {

        String message = "";
        boolean thereAreErrors = bindingResult.hasErrors();
        message = authorDto.getId() != null ? "Book updated successfully!" : "book saved successfully!";
        authorDto = authorService.saveOrUpdateAuthor(authorDto);
        emailSendUtil.sendEmail(authorDto.getEmail());
        if (thereAreErrors) {
            model.addAttribute("authorDto", authorDto);
            return "author/addAuthor";

        }
//        if (authorDto == null) {
//            message = "author cannot be saved";
//        }
        //redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/author/addAuthor";
    }


    @GetMapping("/find-by-id/{id}")
    public String findById(@PathVariable("id") Integer authorId, RedirectAttributes redirectAttributes) {
        AuthorDto authorDto = authorService.findById(authorId);
        if (authorDto != null)
            redirectAttributes.addFlashAttribute("authorDto", authorDto);
        return "redirect:/author/addAuthor";
    }

    @GetMapping("/delete/{authorId}")
    public String deleteAuthor(@PathVariable("authorId") Integer authorId) {
        authorService.deleteAuthorById(authorId);
        return "redirect:/author/home";
    }
}

