package lk.elib.elibrarybackend.service.member;

import lk.elib.elibrarybackend.dto.MemberDto;

import java.util.List;

public interface MemberService {
    List<MemberDto> findAll();

    MemberDto findById(int id);

    void save(MemberDto memberDto);

    MemberDto update(MemberDto memberDto);

    void deleteById(int id);
}
