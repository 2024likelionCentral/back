package com.example.metalog.service;

import com.example.metalog.config.CustomUserDetails;
import com.example.metalog.config.JwtTokenProvider;
import com.example.metalog.dto.AuthRequestDTO;
import com.example.metalog.dto.AuthResponseDTO;
import com.example.metalog.dto.UserProfileRequestDTO;
import com.example.metalog.dto.UserRequestDTO;
import com.example.metalog.entity.Auth;
import com.example.metalog.entity.Role;
import com.example.metalog.entity.User;
import com.example.metalog.repository.AuthRepository;
import com.example.metalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserProfileService userProfileService;


    @Transactional
    public AuthResponseDTO login(AuthRequestDTO requestDto) {
        // 이메일이랑 비번확인
        User user = this.userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다. userName = " + requestDto.getUsername()));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. userEmail = " + requestDto.getUsername());
        }

        // 엑세스랑 리프레시 토큰 확인하기
        String accessToken = this.jwtTokenProvider.generateAccessToken(
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), user.getPassword()));
        String refreshToken = this.jwtTokenProvider.generateRefreshToken(
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), user.getPassword()));

        // 이미 있는지 확인하고 있으면 업데이트
        if (this.authRepository.existsByUser(user)) {
            user.getAuth().updateAccessToken(accessToken);
            user.getAuth().updateRefreshToken(refreshToken);
            return new AuthResponseDTO(user.getAuth(), user.getId());
        }

        // 인증된거 없으면 새로 만들기
        Auth auth = this.authRepository.save(Auth.builder()
                .user(user)
                .tokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());

        return new AuthResponseDTO(auth, user.getId());
    }

    @Transactional
    public void signup(UserRequestDTO requestDto) {
        requestDto.setRole(Role.ROLE_USER);
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = this.userRepository.save(requestDto.toEntity());


        UserProfileRequestDTO userProfileRequestDTO = UserProfileRequestDTO.builder()
                .username(user.getUsername())
                .motto("Default motto")  // 기본 모토 설정
                .profilePicture(null)
                .build();
        userProfileService.createUserProfile(userProfileRequestDTO);

    }

    @Transactional
    public String refreshToken(String refreshToken) {
        // 리프레시 만료확인하고 엑세스토큰업데이트
        if (this.jwtTokenProvider.validateToken(refreshToken)) {
            Auth auth = this.authRepository.findByRefreshToken(refreshToken).orElseThrow(
                    () -> new IllegalArgumentException("해당 REFRESH_TOKEN 을 찾을 수 없습니다.\nREFRESH_TOKEN = " + refreshToken));

            String newAccessToken = this.jwtTokenProvider.generateAccessToken(
                    new UsernamePasswordAuthenticationToken(
                            new CustomUserDetails(auth.getUser()), auth.getUser().getPassword()));
            auth.updateAccessToken(newAccessToken);
            return newAccessToken;
        }


        return null;
    }
}
