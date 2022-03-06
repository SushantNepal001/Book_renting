package com.rastapi.brs.service;

import com.rastapi.brs.Dto.MemberDto;

import java.util.List;


public interface MemberService {
    MemberDto saveMember(MemberDto memberDto);
    List<MemberDto> findAllMember();
    MemberDto findById(Integer memberId);
    void deleteMemberById(Integer id);
}
