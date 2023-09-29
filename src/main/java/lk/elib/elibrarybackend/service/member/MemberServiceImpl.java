package lk.elib.elibrarybackend.service.member;

import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.entity.Member;
import lk.elib.elibrarybackend.entity.Role;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream().map(
                member -> modelMapper.map(member, MemberDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto findById(int id) {
        Optional<Member> optMember = memberRepository.findById(id);

        if (optMember.isPresent()) {
            return modelMapper.map(optMember.get(), MemberDto.class);

        } else {
            throw new ResourceNotFoundException("Invalid member id - " + id);
        }
    }

    @Override
    public MemberDto save(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);

        Role role = new Role(1,"ROLE_MEMBER");
        Set<Role> roleSet = new LinkedHashSet<>();
        roleSet.add(role);
        member.getUser().setRoles(roleSet);

        return modelMapper.map(memberRepository.save(member), MemberDto.class);
    }

    @Override
    public MemberDto update(MemberDto memberDto) {
        Optional<Member> optMember = memberRepository.findById(memberDto.getId());

        if (optMember.isPresent()) {
            Member member = modelMapper.map(memberDto, Member.class);
            member.getUser().setId(member.getId());

            return modelMapper.map(memberRepository.save(member), MemberDto.class);

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
