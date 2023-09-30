package lk.elib.elibrarybackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
