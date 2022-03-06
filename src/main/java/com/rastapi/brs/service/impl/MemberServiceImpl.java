package com.rastapi.brs.service.impl;

import com.rastapi.brs.Dto.MemberDto;
import com.rastapi.brs.entities.Member;
import com.rastapi.brs.repo.MemberRepo;
import com.rastapi.brs.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepo memberRepo;

    @Override
    public MemberDto saveMember(MemberDto memberDto) {
        Member entity = new Member().builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .mobileNumber(memberDto.getMobileNumber())
                .address(memberDto.getAddress())
                .build();
        entity = memberRepo.save(entity);

        return MemberDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .build();
    }

    @Override
    public List<MemberDto> findAllMember() {
        List<Member> memberList = memberRepo.findAll();
        return memberList.stream().map(entity -> MemberDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .mobileNumber(entity.getMobileNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .build()).collect(Collectors.toList());
    }

    @Override
    public MemberDto findById(Integer memberId) {
        Member m;
        Optional<Member> optionalMember = memberRepo.findById(memberId);
        if (optionalMember.isPresent()) {
            m = optionalMember.get();
            return MemberDto.builder()
                    .id(m.getId())
                    .name(m.getName())
                    .mobileNumber(m.getMobileNumber())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteMemberById(Integer id) {
        memberRepo.deleteById(id);

    }
}
