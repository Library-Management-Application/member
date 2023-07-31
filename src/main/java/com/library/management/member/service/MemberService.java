package com.library.management.member.service;


import com.library.management.member.domain.Member;
import com.library.management.member.model.MemberDto;
import com.library.management.member.repo.MemberRepository;
import com.library.management.member.web.exception.BadRequestException;
import com.library.management.member.web.exception.NotFoundException;
import com.library.management.member.web.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;


    // Add new member
    public MemberDto addNewMember(MemberDto memberDto) {
        Member member = memberMapper.memberDtoToMember(memberDto);

        Member memberSaved = memberRepository.save(member);
        return memberMapper.memberToMemberDto(memberSaved);
    }

    // Update member
    public MemberDto updateMember(MemberDto memberDto) {
        if (memberDto.getId() == null) {
            throw new BadRequestException("Member Id cant be null.");
        }

                    Member memberFound = memberRepository.findById(memberDto.getId()).
                orElseThrow(() -> new NotFoundException(String.format("memberId -> %s not found!", memberDto.getId().toString())));
        memberMapper.memberDtoToMember(memberDto, memberFound);
        Member memberSaved = memberRepository.save(memberFound);
        return memberMapper.memberToMemberDto(memberSaved);
    }

    // Remove member
    public void removeMember(Long memberId) {
        Member memberFound = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(String.format("memberId -> %s not found!", memberId.toString())));
        memberRepository.delete(memberFound);
    }

    public MemberDto findMemberById(Long memberId)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(String.format("memberId -> %s not found!", memberId.toString())));
        return memberMapper.memberToMemberDto(member);
    }

    public List<String> getEmailAddressAll() {
        final List<Member> members = memberRepository.findAll();

        List<String> membersEmails = new ArrayList<>();

        final Date today = new Date();
        if (members != null)
        {
            membersEmails = members.stream().filter(m -> m.getMembershipExpirationDate().after(today))
                    .map(m-> m.getEmail()).collect(Collectors.toList());
        }

        return membersEmails;
    }
}
