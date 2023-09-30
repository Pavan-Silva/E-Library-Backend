package lk.elib.elibrarybackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    Integer id;

    @NotBlank
    UserDto user;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String mobile;
}