package com.rastapi.brs.controller;
import com.rastapi.brs.Dto.MemberDto;
import com.rastapi.brs.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    public MemberService memberService;

    @GetMapping
    public String memberMainPage(Model model) {
        model.addAttribute( "memberDtoList", memberService.findAllMember());
        return "member/member";

    }

    @GetMapping("/addMember")
    public String addMember(Model model) {
        if (model.getAttribute("memberDto") == null)
            model.addAttribute("memberDto", new MemberDto());
        return "member/addMember";
    }

    @PostMapping
    public String saveMember(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {
        String message = "";
        memberDto = memberService.saveMember(memberDto);
        if (memberDto == null) {
            message = "Member cannot be saved";
        } else {
            message = memberDto.getId() != null ? "Member updated sucessfully!" : "Member saved sucessfully!";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/member/addMember";
    }

    @GetMapping("/find-by-id/{id}")
    public String findbyId(@PathVariable("id") Integer memberId, RedirectAttributes redirectAttributes) {
        MemberDto memberDto = memberService.findById(memberId);
        if (memberDto != null)
            redirectAttributes.addFlashAttribute("memberDto", memberDto);
        return "redirect:/member/addMember";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") Integer memberId) {
        memberService.deleteMemberById(memberId);
        return "redirect:/member";
    }



}