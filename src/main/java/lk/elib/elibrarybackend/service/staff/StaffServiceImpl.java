package lk.elib.elibrarybackend.service.staff;

import lk.elib.elibrarybackend.entity.StaffMember;
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
            throw new RuntimeException();
        }
    }

    @Override
    public StaffMember save(StaffMember staffMember) {
        return staffRepository.save(staffMember);
    }

    @Override
    public void deleteById(int id) {
        staffRepository.deleteById(id);
    }
}
