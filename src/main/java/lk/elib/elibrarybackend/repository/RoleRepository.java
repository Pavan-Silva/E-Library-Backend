package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}