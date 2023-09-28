package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}