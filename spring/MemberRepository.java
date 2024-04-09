package com.elice.elcademy.member.repository;

import com.elice.elcademy.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    List<Member> findAll();

    Optional<Member> findById(Long memberId);

    Member save(Member member);

    void delete(Member member);
}
