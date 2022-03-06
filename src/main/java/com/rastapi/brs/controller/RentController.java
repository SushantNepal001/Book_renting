package com.rastapi.brs.controller;

import com.rastapi.brs.Dto.AuthorDto;
import com.rastapi.brs.Dto.RentDto;
import com.rastapi.brs.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class RentController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/rent")
    public String rentMainPage(Model model){

        model.addAttribute("rentDtoList",transactionService.findAllTransaction());
        return "transaction/rent";
    }

    @GetMapping("/addRent")
    public String create(Model model){
        if (model.getAttribute("rentDto") == null)
            model.addAttribute("rentDto", new RentDto());
        return "trasaction/addRent";
    }



}
