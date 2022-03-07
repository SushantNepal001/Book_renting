package com.rastapi.brs.controller;

import com.rastapi.brs.Dto.AuthorDto;
import com.rastapi.brs.entities.Author;
import com.rastapi.brs.service.AuthorService;
import com.rastapi.brs.utils.EmailSendUtil;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String saveAuthor(@ModelAttribute AuthorDto authorDto, RedirectAttributes redirectAttributes) {
        String message = "";
        authorDto = authorService.saveOrUpdateAuthor(authorDto);
        emailSendUtil.sendEmail(authorDto.getEmail());
        if (authorDto == null) {
            message = "author cannot be saved";
        } else {
            message = authorDto.getId() != null ? "author updated sucessfully and email has been sent" : "author saved sucessfully and email has been sent";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/author/addAuthor";
    }

//    @PostMapping
//    public  String sendEmail(@ModelAttribute AuthorDto authorDto){
//
//        return "redirect:/author/addAuthor";
//    }
    @GetMapping("/find-by-id/{id}")
    public String findbyId(@PathVariable("id") Integer authorId, RedirectAttributes redirectAttributes) {
        AuthorDto authorDto = authorService.findById(authorId);
        if (authorDto != null)
            redirectAttributes.addFlashAttribute("authorDto", authorDto);
        return "redirect:/author/addAuthor";
    }
    @GetMapping("/delete/{authorId}")
    public String deleteAuthor(@PathVariable("authorId") Integer authorId){
        authorService.deleteAuthorById(authorId);
        return "redirect:/author/home";
    }
}

