package lk.elib.elibrarybackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    Integer id;
    UserDto user;
    String firstName;
    String lastName;
    String email;
    String mobile;
}