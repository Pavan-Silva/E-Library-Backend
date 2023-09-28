package lk.elib.elibrarybackend.service.role;

import lk.elib.elibrarybackend.entity.Role;
import lk.elib.elibrarybackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void setRole(int id, String authority) {
        Role role = new Role();
        role.setId(id);
        role.setAuthority(authority);

        roleRepository.save(role);
    }
}
