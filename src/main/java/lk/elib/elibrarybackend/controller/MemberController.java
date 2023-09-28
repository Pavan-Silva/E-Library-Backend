package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.entity.Member;
import lk.elib.elibrarybackend.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> findAllMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable int id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Member> addNewMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.save(member));
    }

    @PutMapping
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.save(member));
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable int id) {
        memberService.deleteById(id);
    }
}
