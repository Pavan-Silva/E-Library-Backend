package lk.elib.elibrarybackend.service.member;

import lk.elib.elibrarybackend.entity.Member;
import lk.elib.elibrarybackend.entity.Role;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
            throw new ResourceNotFoundException("Invalid member id - " + id);
        }
    }

    @Override
    public Member save(Member member) {
        Role role = new Role(1,"ROLE_MEMBER");

        Set<Role> roleSet = new LinkedHashSet<>();
        roleSet.add(role);

        member.getUser().setRoles(roleSet);

        return memberRepository.save(member);
    }

    @Override
    public Member update(Member member) {
        Optional<Member> optMember = memberRepository.findById(member.getId());

        if (optMember.isPresent()) {
            member.getUser().setId(member.getId());
            return memberRepository.save(member);

        } else {
            throw new ResourceNotFoundException("Invalid member id - " + member.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<Member> optMember = memberRepository.findById(id);

        if (optMember.isPresent()) {
            memberRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Invalid member id - " + id);
        }
    }
}
