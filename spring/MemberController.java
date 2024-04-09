package com.elice.elcademy.member.controller;

import com.elice.elcademy.member.entity.Member;
import com.elice.elcademy.member.entity.MemberPostDto;
import com.elice.elcademy.member.mapper.MemberMapper;
import com.elice.elcademy.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable("memberId") long memberId) {
        Member findMember = memberService.findMember(memberId);

        if (findMember == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(findMember, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> members = memberService.findMembers();

        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostDto memberPostDto) {
        Member member = mapper.memberPostDtoToMember(memberPostDto);
        Member newMember = memberService.createMember(member);

        if (newMember == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(newMember), HttpStatus.CREATED);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable("memberId") long memberId, @RequestBody Member member) {
        member.setMemberId(memberId);
        Member updatedMember = memberService.updateMember(member);

        if (updatedMember == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable("memberId") long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
