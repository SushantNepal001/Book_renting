package com.spring.brs.controller;

import com.spring.brs.Dto.BookTransactionDto;
import com.spring.brs.service.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@RequestMapping("/transaction")
public class ReturnController {
    @Autowired
    public BookTransactionService bookTransactionService;

    @GetMapping("/return")
    public String memberMainPage(Model model) {
        model.addAttribute( "transactionDtoList", bookTransactionService.findAllTransaction());
        return "transaction/Return";

    }

    @GetMapping("/addReturn")
    public String addMember(Model model) {
        if (model.getAttribute("bookTransactionDto") == null)
            model.addAttribute("bookTransactionDto", new BookTransactionDto());
//            model.addAttribute("codes",bookTransactionService.findAllTransactionCode());
        return "transaction/addReturn";
    }

    @PostMapping
    public String saveMember(@ModelAttribute BookTransactionDto bookTransactionDto, RedirectAttributes redirectAttributes) throws ParseException {
        String message = "";
        bookTransactionDto = bookTransactionService.saveTransaction(bookTransactionDto);
        if (bookTransactionDto == null) {
            message = "Member cannot be saved";
        } else {
            message = bookTransactionDto.getId() != null ? "Member saved sucessfully!" : "Member saved sucessfully!";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/transaction/addReturn";
    }

    @GetMapping("return/addReturn/find-by-id/{id}")
    public String findById(@PathVariable("id") Integer transactionId, RedirectAttributes redirectAttributes) {
        BookTransactionDto bookTransactionDto = bookTransactionService.findById(transactionId);
        if (bookTransactionDto != null)
            redirectAttributes.addFlashAttribute("bookTransactionDto", bookTransactionDto);
        return "redirect:/transaction/addReturn";
    }

    @GetMapping("return/delete/{id}")
    public String deleteMember(@PathVariable("id") Integer transactionId) {
        bookTransactionService.deleteBookTransactionById(transactionId);
        return "redirect:/transaction/addReturn";
    }

}
