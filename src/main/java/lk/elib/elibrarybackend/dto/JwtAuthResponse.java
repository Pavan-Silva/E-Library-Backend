package lk.elib.elibrarybackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtAuthResponse {

    private String tokenType = "Bearer";
    private String accessToken;
    private String refreshToken;
}
