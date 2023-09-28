package lk.elib.elibrarybackend.service.member;

import lk.elib.elibrarybackend.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();

    Member findById(int id);

    Member save(Member member);

    Member update(Member member);

    void deleteById(int id);
}
