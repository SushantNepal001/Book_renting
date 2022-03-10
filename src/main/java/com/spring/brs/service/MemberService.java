package com.spring.brs.service;

import com.spring.brs.Dto.MemberDto;

import java.util.List;


public interface MemberService {
    MemberDto saveMember(MemberDto memberDto);
    List<MemberDto> findAllMember();
    MemberDto findById(Integer memberId);
    void deleteMemberById(Integer id);
}
