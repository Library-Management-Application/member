package com.library.management.member.web.mappers;


import com.library.management.member.domain.Member;
import com.library.management.member.model.MemberDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(MemberMapperDecorator.class)
public interface MemberMapper {

    MemberDto memberToMemberDto(Member member);

    Member memberDtoToMember(MemberDto dto);

    void memberDtoToMember(MemberDto memberDto, @MappingTarget Member member);
}