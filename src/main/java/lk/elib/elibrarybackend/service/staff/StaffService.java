package lk.elib.elibrarybackend.service.staff;

import lk.elib.elibrarybackend.entity.StaffMember;

import java.util.List;

public interface StaffService {
    List<StaffMember> findAll();
    StaffMember findById(int id);
    StaffMember save(StaffMember staffMember);
    void deleteById(int id);
}
