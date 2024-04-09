package com.elice.elcademy.member.mapper;

import com.elice.elcademy.member.entity.Member;
import com.elice.elcademy.member.entity.MemberPostDto;
import com.elice.elcademy.member.entity.MemberResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostDtoToMember(MemberPostDto memberPostDto);

    MemberResponseDto memberToMemberResponseDto(Member member);

}
