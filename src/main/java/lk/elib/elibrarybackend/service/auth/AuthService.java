package lk.elib.elibrarybackend.service.auth;

import lk.elib.elibrarybackend.dto.JwtAuthResponse;
import lk.elib.elibrarybackend.dto.LoginDto;
import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.dto.TokenRequest;

public interface AuthService {
    JwtAuthResponse login(LoginDto loginDto);

    JwtAuthResponse register(MemberDto memberDto);

    JwtAuthResponse refreshToken(TokenRequest tokenRequest);
}
