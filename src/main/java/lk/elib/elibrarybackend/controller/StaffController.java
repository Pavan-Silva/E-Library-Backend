package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.entity.StaffMember;
import lk.elib.elibrarybackend.service.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffMember>> findAllStaffMembers() {
        return ResponseEntity.ok(staffService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffMember> findStaffMemberById(@PathVariable int id) {
        return ResponseEntity.ok(staffService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StaffMember> addNewStaffMember(@RequestBody StaffMember staffMember) {
        return ResponseEntity.ok(staffService.save(staffMember));
    }

    @PutMapping
    public ResponseEntity<StaffMember> updateStaffMember(@RequestBody StaffMember staffMember) {
        staffMember.getUser().setId(staffMember.getId());
        return ResponseEntity.ok(staffService.save(staffMember));
    }

    @DeleteMapping("/{id}")
    public void deleteStaffMemberById(@PathVariable int id) {
        staffService.deleteById(id);
    }
}
