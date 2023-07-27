package com.library.management.member.web.mappers;

import com.library.management.member.domain.Member;
import com.library.management.member.model.MemberDto;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class MemberMapperDecorator implements MemberMapper {
    private MemberMapper mapper;

    @Autowired
    public void setMapper(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public MemberDto memberToMemberDto(Member member) {
       return mapper.memberToMemberDto(member);
    }

    @Override
    public Member memberDtoToMember(MemberDto memberDto) {
        return mapper.memberDtoToMember(memberDto);
    }

    @Override
    public void memberDtoToMember(MemberDto memberDto, Member member){
        mapper.memberDtoToMember(memberDto, member);
    }

}
