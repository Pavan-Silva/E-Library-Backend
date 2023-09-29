package lk.elib.elibrarybackend.service.staff;

import lk.elib.elibrarybackend.dto.StaffMemberDto;

import java.util.List;

public interface StaffService {

    List<StaffMemberDto> findAll();

    StaffMemberDto findById(int id);

    StaffMemberDto save(StaffMemberDto staffMemberDto);

    StaffMemberDto update(StaffMemberDto staffMemberDto);

    void deleteById(int id);
}