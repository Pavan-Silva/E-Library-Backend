package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<StaffMember, Integer> {
}