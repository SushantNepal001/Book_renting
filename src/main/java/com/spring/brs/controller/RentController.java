package com.spring.brs.controller;

import com.spring.brs.Dto.BookTransactionDto;
import com.spring.brs.service.BookService;
import com.spring.brs.service.BookTransactionService;
import com.spring.brs.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/transaction")
public class RentController {

    @Autowired
    private BookTransactionService bookTransactionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/rent")
    public String rentMainPage(Model model){

        model.addAttribute("transactionDtoList", bookTransactionService.findAllTransaction());
        return "transaction/Rent";
    }

    @GetMapping("/addRent")
    public String addRent(Model model) {
        if (model.getAttribute("bookTransactionDto") == null)
            model.addAttribute("bookTransactionDto", new BookTransactionDto());
            model.addAttribute("books",bookService.findAllBook());
            model.addAttribute("members",memberService.findAllMember());
        return "transaction/addRent";
    }

    @PostMapping("/addRent")
    public String saveRent(@ModelAttribute BookTransactionDto bookTransactionDto, RedirectAttributes redirectAttributes) throws  ParseException {
        String message = "";
        message = bookTransactionDto.getId() != null ? "rent updated successfully!" : "rent saved successfully!";
        bookTransactionDto = bookTransactionService.saveTransaction(bookTransactionDto);
        if (bookTransactionDto == null) {
            message = "rent cannot be saved";
        } else {

        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/transaction/addRent";
    }

    @GetMapping("/find-by-id/{id}")
    public String findById(@PathVariable("id") Integer bookTransactionId, RedirectAttributes redirectAttributes) {

        BookTransactionDto bookTransactionDto = bookTransactionService.findById(bookTransactionId);
        if (bookTransactionDto != null)
            redirectAttributes.addFlashAttribute("bookTransactionDto", bookTransactionDto);
        return "redirect:/transaction/addRent";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:transaction/rent";
    }



}
