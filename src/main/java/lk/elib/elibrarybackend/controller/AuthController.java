package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.dto.JwtAuthResponse;
import lk.elib.elibrarybackend.dto.LoginDto;
import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setAccessToken(token);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> registerUser(@RequestBody MemberDto memberDto) {
        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setAccessToken(authService.register(memberDto));

        return ResponseEntity.ok(authResponse);
    }
}
