package com.rastapi.brs.controller;

import com.rastapi.brs.Dto.BookDto;
import com.rastapi.brs.Dto.BookTransactionDto;
import com.rastapi.brs.Dto.RentDto;
import com.rastapi.brs.service.BookService;
import com.rastapi.brs.service.BookTransactionService;
import com.rastapi.brs.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.DocFlavor;
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
    public String saveRent(@ModelAttribute BookTransactionDto bookTransactionDto, RedirectAttributes redirectAttributes) throws IOException, ParseException {
        String message = "";
        bookTransactionDto = bookTransactionService.saveTransaction(bookTransactionDto);
        if (bookTransactionDto == null) {
            message = "rent cannot be saved";
        } else {
            message = bookTransactionDto.getId() != null ? "rent updated sucessfully!" : "book saved sucessfully!";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/transaction/addRent";
    }

    @GetMapping("/find-by-id/{id}")
    public String findbyId(@PathVariable("id") Integer bookTransactionId, RedirectAttributes redirectAttributes) {
        BookTransactionDto bookTransactionDto = bookTransactionService.findById(bookTransactionId);
        if (bookTransactionDto != null)
            redirectAttributes.addFlashAttribute("bookTransactionDto", bookTransactionId);
        return "redirect:/transaction/addRent";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:transaction/rent";
    }



}
