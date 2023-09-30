package lk.elib.elibrarybackend.service.auth;

import lk.elib.elibrarybackend.dto.LoginDto;
import lk.elib.elibrarybackend.dto.MemberDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(MemberDto memberDto);
}
