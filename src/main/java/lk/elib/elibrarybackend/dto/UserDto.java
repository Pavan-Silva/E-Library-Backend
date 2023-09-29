package lk.elib.elibrarybackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    Integer id;
    Byte active;
    Set<RoleDto> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}