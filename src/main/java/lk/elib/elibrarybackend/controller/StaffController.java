package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.entity.StaffMember;
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
    public List<StaffMember> findAllStaffMembers() {
        return staffService.findAll();
    }

    @GetMapping("/{id}")
    public StaffMember findStaffMemberById(@PathVariable int id) {
        return staffService.findById(id);
    }

    @PostMapping
    public StaffMember addNewStaffMember(@RequestBody StaffMember staffMember) {
        return staffService.save(staffMember);
    }

    @PutMapping
    public StaffMember updateStaffMember(@RequestBody StaffMember staffMember) {
        return staffService.update(staffMember);
    }

    @DeleteMapping("/{id}")
    public void deleteStaffMemberById(@PathVariable int id) {
        staffService.deleteById(id);
    }
}
