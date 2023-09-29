package lk.elib.elibrarybackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    Integer id;

    @JsonIgnore
    String username;

    @JsonIgnore
    String password;

    Byte active;
    Set<RoleDto> roles;
}