package lk.elib.elibrarybackend.service.auth;

import lk.elib.elibrarybackend.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
