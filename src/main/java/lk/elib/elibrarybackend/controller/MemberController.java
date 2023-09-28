package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.entity.Member;
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
    public List<Member> findAllMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Member findMemberById(@PathVariable int id) {
        return memberService.findById(id);
    }

    @PostMapping
    public Member addNewMember(@RequestBody Member member) {
        return memberService.save(member);
    }

    @PutMapping
    public Member updateMember(@RequestBody Member member) {
        return memberService.update(member);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable int id) {
        memberService.deleteById(id);
    }
}
