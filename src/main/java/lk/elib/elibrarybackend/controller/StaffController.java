package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.dto.StaffMemberDto;
import lk.elib.elibrarybackend.service.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public List<StaffMemberDto> findAllStaffMembers() {
        return staffService.findAll();
    }

    @GetMapping("/{id}")
    public StaffMemberDto findStaffMemberById(@PathVariable int id) {
        return staffService.findById(id);
    }

    @PostMapping
    public StaffMemberDto addNewStaffMember(@RequestBody StaffMemberDto staffMemberDto) {
        return staffService.save(staffMemberDto);
    }

    @PutMapping
    public StaffMemberDto updateStaffMember(@RequestBody StaffMemberDto staffMemberDto) {
        return staffService.update(staffMemberDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStaffMemberById(@PathVariable int id) {
        staffService.deleteById(id);
    }
}
