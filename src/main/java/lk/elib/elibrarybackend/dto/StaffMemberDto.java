package lk.elib.elibrarybackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StaffMemberDto {

    private Integer id;

    @NotNull
    private UserDto user;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String nic;

    @NotNull
    private String mobile;

    @NotNull
    private GenderDto gender;

    @NotNull
    private DesignationDto designation;
}