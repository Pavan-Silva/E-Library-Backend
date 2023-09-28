package lk.elib.elibrarybackend.service.staff;

import lk.elib.elibrarybackend.entity.StaffMember;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public List<StaffMember> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public StaffMember findById(int id) {
        Optional<StaffMember> optStaffMember = staffRepository.findById(id);

        if (optStaffMember.isPresent()) {
            return optStaffMember.get();

        } else {
            throw new ResourceNotFoundException("Invalid staff id - " + id);
        }
    }

    @Override
    public StaffMember save(StaffMember staffMember) {
        return staffRepository.save(staffMember);
    }

    @Override
    public StaffMember update(StaffMember staffMember) {
        Optional<StaffMember> optStaffMember = staffRepository.findById(staffMember.getId());

        if (optStaffMember.isPresent()) {
            staffMember.getUser().setId(staffMember.getId());
            return staffRepository.save(staffMember);

        } else {
            throw new ResourceNotFoundException("Invalid staff id - " + staffMember.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<StaffMember> optStaffMember = staffRepository.findById(id);

        if (optStaffMember.isPresent()) {
            staffRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Invalid staff id - " + id);
        }
    }
}
