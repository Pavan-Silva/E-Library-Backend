package lk.elib.elibrarybackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @JsonIgnore
    Integer id;

    @NotNull
    Boolean active;

    @NotNull
    List<RoleDto> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}