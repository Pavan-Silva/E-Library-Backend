package lk.elib.elibrarybackend.service.member;

import lk.elib.elibrarybackend.entity.Member;
import lk.elib.elibrarybackend.repository.MemberRepository;
import lk.elib.elibrarybackend.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleService roleService;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(int id) {
        Optional<Member> optMember = memberRepository.findById(id);

        if (optMember.isPresent()) {
            return optMember.get();

        } else {
            throw new RuntimeException("Invalid Member Id - " + id);
        }
    }

    @Override
    public Member save(Member member) {
        Member memberResult = memberRepository.save(member);

        if (memberResult.getId() != null) {
            roleService.setRole(memberResult.getId(), "ROLE_MEMBER");
        }

        return memberResult;
    }

    @Override
    public Member update(Member member) {
        Optional<Member> optMember = memberRepository.findById(member.getId());

        if (optMember.isPresent()) {
            member.getUser().setId(member.getId());
            return memberRepository.save(member);

        } else {
            throw new RuntimeException("Invalid Member Id - " + member.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<Member> optMember = memberRepository.findById(id);

        if (optMember.isPresent()) {
            memberRepository.deleteById(id);

        } else {
            throw new RuntimeException("Invalid Member Id - " + id);
        }
    }
}
