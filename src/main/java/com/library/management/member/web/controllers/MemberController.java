package com.library.management.member.web.controllers;


import com.library.management.member.model.MemberDto;
import com.library.management.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    //Get member details
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDto> findMemberById(@PathVariable("memberId") Long memberId)
    {
        return new ResponseEntity<>(memberService.findMemberById(memberId), HttpStatus.OK);
    }

    //Get member details
    @GetMapping("/all-emails")
    public ResponseEntity<List<String>> getEmailAddressAll()
    {
        return new ResponseEntity<>(memberService.getEmailAddressAll(), HttpStatus.OK);
    }

    // Add new member
    @PostMapping
    public ResponseEntity<MemberDto> addNewMember(@RequestBody @Validated MemberDto memberDto) {
        return new ResponseEntity<>(memberService.addNewMember(memberDto), HttpStatus.CREATED);
    }

    // update member
    @PutMapping
    public ResponseEntity<MemberDto> updateMember(@RequestBody @Validated MemberDto memberDto) {
        return new ResponseEntity<>(memberService.updateMember(memberDto), HttpStatus.NO_CONTENT);
    }

    // remove member
    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(@PathVariable("memberId") Long memberId) {
        memberService.removeMember(memberId);
    }
}
