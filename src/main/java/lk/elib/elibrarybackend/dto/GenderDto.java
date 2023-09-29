package lk.elib.elibrarybackend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenderDto {

    private Integer id;

    @NotNull
    @Size(max = 45)
    private String name;
}