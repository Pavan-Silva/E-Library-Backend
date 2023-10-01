package lk.elib.elibrarybackend.service.auth;

import lk.elib.elibrarybackend.dto.JwtAuthResponse;
import lk.elib.elibrarybackend.dto.LoginDto;
import lk.elib.elibrarybackend.dto.MemberDto;
import lk.elib.elibrarybackend.dto.TokenRequest;
import lk.elib.elibrarybackend.exception.AuthenticationException;
import lk.elib.elibrarybackend.exception.UserAlreadyExistsException;
import lk.elib.elibrarybackend.security.CustomUserDetailsService;
import lk.elib.elibrarybackend.security.JwtTokenProvider;
import lk.elib.elibrarybackend.service.member.MemberService;
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
        String username = userDetailsService.getUsernameByEmail(loginDto.getEmail());

        if (StringUtils.hasText(username)) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username, loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            JwtAuthResponse authResponse = new JwtAuthResponse();
            authResponse.setAccessToken(jwtTokenProvider.generateToken(authentication));
            authResponse.setRefreshToken(jwtTokenProvider.generateRefreshToken(username));

            return authResponse;

        } else {
            throw new AuthenticationException(null);
        }
    }

    @Override
    public JwtAuthResponse register(MemberDto memberDto) {

        if (userDetailsService.userExistsWithEmail(memberDto.getUser().getEmail())) {
            throw new UserAlreadyExistsException("Try another email");

        } else {
            if (userDetailsService.userExistsWithUsername("")) {
                throw new UserAlreadyExistsException("Try another username");

            } else {
                memberService.save(memberDto);

                LoginDto login = new LoginDto();
                login.setEmail(memberDto.getUser().getEmail());
                login.setPassword(memberDto.getUser().getPassword());

                return login(login);
            }
        }
    }

    @Override
    public JwtAuthResponse refreshToken(TokenRequest tokenRequest) {
        JwtAuthResponse authResponse = new JwtAuthResponse();

        String token = jwtTokenProvider.refreshToken(tokenRequest.getToken());

        if (StringUtils.hasText(token)) {
            authResponse.setAccessToken(token);
            authResponse.setRefreshToken(jwtTokenProvider.generateRefreshToken(
                    jwtTokenProvider.getUsername(tokenRequest.getToken())
            ));
        }

        return authResponse;
    }
}
