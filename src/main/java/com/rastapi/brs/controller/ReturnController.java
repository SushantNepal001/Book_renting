package com.rastapi.brs.controller;

import com.rastapi.brs.Dto.BookTransactionDto;
import com.rastapi.brs.Dto.MemberDto;
import com.rastapi.brs.service.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@RequestMapping("/transaction/Return")
public class ReturnController {
    @Autowired
    public BookTransactionService bookTransactionService;

    @GetMapping
    public String memberMainPage(Model model) {
        model.addAttribute( "transactionDtoList", bookTransactionService.findAllTransaction());
        return "transaction/Return";

    }

    @GetMapping("/addReturn")
    public String addMember(Model model) {
        if (model.getAttribute("bookTransactionDto") == null)
            model.addAttribute("bookTransactionDto", new BookTransactionDto());
            model.addAttribute("codes",bookTransactionService.findAllTransaction());
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
        return "redirect:/member/addMember";
    }

    @GetMapping("/find-by-id/{id}")
    public String findbyId(@PathVariable("id") Integer transactionId, RedirectAttributes redirectAttributes) {
        BookTransactionDto bookTransactionDto = bookTransactionService.findById(transactionId);
        if (bookTransactionDto != null)
            redirectAttributes.addFlashAttribute("memberDto", bookTransactionDto);
        return "redirect:/transaction/addReturn";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") Integer transactionId) {
        bookTransactionService.deleteBookTransactionById(transactionId);
        return "redirect:/transaction/addReturn";
    }

}
