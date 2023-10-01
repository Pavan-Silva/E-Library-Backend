package lk.elib.elibrarybackend.service.auth;

import lk.elib.elibrarybackend.dto.JwtAuthResponse;
import lk.elib.elibrarybackend.dto.LoginDto;
import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.dto.TokenRequest;
import lk.elib.elibrarybackend.exception.UserAlreadyExistsException;
import lk.elib.elibrarybackend.security.JwtTokenProvider;
import lk.elib.elibrarybackend.service.member.MemberService;
import lk.elib.elibrarybackend.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setAccessToken(jwtTokenProvider.generateToken(authentication));
        authResponse.setRefreshToken(jwtTokenProvider.generateRefreshToken(loginDto.getEmail()));

        return authResponse;
    }

    @Override
    public JwtAuthResponse register(MemberDto memberDto) {

        if (userDetailsService.userExistsWithEmail(memberDto.getUser().getEmail())) {
            throw new UserAlreadyExistsException("Try another email");

        } else {
            memberService.save(memberDto);

            LoginDto login = new LoginDto();
            login.setEmail(memberDto.getUser().getEmail());
            login.setPassword(memberDto.getUser().getPassword());

            return login(login);
        }
    }

    @Override
    public JwtAuthResponse refreshToken(TokenRequest tokenRequest) {
        JwtAuthResponse authResponse = new JwtAuthResponse();

        String token = jwtTokenProvider.refreshToken(tokenRequest.getToken());

        if (StringUtils.hasText(token)) {
            authResponse.setAccessToken(token);
            authResponse.setRefreshToken(jwtTokenProvider.generateRefreshToken(
                    jwtTokenProvider.getEmail(tokenRequest.getToken())
            ));
        }

        return authResponse;
    }
}
