package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDto> findAllMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberDto findMemberById(@PathVariable int id) {
        return memberService.findById(id);
    }

    @PutMapping
    public MemberDto updateMember(@RequestBody MemberDto memberDto) {
        return memberService.update(memberDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable int id) {
        memberService.deleteById(id);
    }
}
