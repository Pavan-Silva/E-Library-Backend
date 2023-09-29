package lk.elib.elibrarybackend.service.member;

import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.entity.Member;
import lk.elib.elibrarybackend.entity.Role;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.repository.MemberRepository;
import lk.elib.elibrarybackend.repository.RoleRepository;
import lk.elib.elibrarybackend.util.Mapper;
import lk.elib.elibrarybackend.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;

    @Override
    public List<MemberDto> findAll() {
        return Mapper.memberListToDto(memberRepository.findAll());
    }

    @Override
    public MemberDto findById(int id) {
        Optional<Member> optMember = memberRepository.findById(id);

        if (optMember.isPresent()) {
            return Mapper.memberToDto(optMember.get());

        } else {
            throw new ResourceNotFoundException("Invalid member id - " + id);
        }
    }

    @Override
    public MemberDto save(MemberDto memberDto) {
        Member member = Mapper.dtoToMember(memberDto);

        member.getUser().setPassword(PasswordEncoder.encode(
                member.getUser().getPassword()
        ));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_MEMBER"));
        member.getUser().setRoles(roles);

        return Mapper.memberToDto(memberRepository.save(member));
    }

    @Override
    public MemberDto update(MemberDto memberDto) {
        Optional<Member> optMember = memberRepository.findById(memberDto.getId());

        if (optMember.isPresent()) {
            Member member = Mapper.dtoToMember(memberDto);
            member.getUser().setId(member.getId());

            return Mapper.memberToDto(memberRepository.save(member));

        } else {
            throw new ResourceNotFoundException("Invalid member id - " + memberDto.getId());
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
