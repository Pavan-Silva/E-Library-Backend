package lk.elib.elibrarybackend.service.staff;

import lk.elib.elibrarybackend.dto.StaffMemberDto;
import lk.elib.elibrarybackend.entity.Role;
import lk.elib.elibrarybackend.entity.StaffMember;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.repository.RoleRepository;
import lk.elib.elibrarybackend.repository.StaffRepository;
import lk.elib.elibrarybackend.security.PasswordEncoder;
import lk.elib.elibrarybackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<StaffMemberDto> findAll() {
        return Mapper.staffListToDto(staffRepository.findAll());
    }

    @Override
    public StaffMemberDto findById(int id) {
        Optional<StaffMember> optStaffMember = staffRepository.findById(id);

        if (optStaffMember.isPresent()) {
            return Mapper.staffMemberToDto(optStaffMember.get());

        } else {
            throw new ResourceNotFoundException("Invalid staff id - " + id);
        }
    }

    @Override
    public StaffMemberDto save(StaffMemberDto staffMemberDto) {
        StaffMember staffMember = Mapper.dtoToStaffMember(staffMemberDto);

        staffMember.getUser().setPassword(PasswordEncoder.encode(
                staffMember.getUser().getPassword()
        ));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_MEMBER"));
        roles.add(roleRepository.findByName("ROLE_STAFF"));

        staffMember.getUser().setRoles(roles);

        return Mapper.staffMemberToDto(staffRepository.save(staffMember));
    }

    @Override
    public StaffMemberDto update(StaffMemberDto staffMemberDto) {
        if (staffRepository.existsById(staffMemberDto.getId())) {
            StaffMember staffMember = Mapper.dtoToStaffMember(staffMemberDto);

            staffMember.getUser().setId(staffMember.getId());

            return Mapper.staffMemberToDto(staffRepository.save(staffMember));

        } else {
            throw new ResourceNotFoundException("Invalid staff id - " + staffMemberDto.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Invalid staff id - " + id);
        }
    }
}
