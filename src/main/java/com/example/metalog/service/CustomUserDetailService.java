package com.example.metalog.service;

import com.example.metalog.config.CustomUserDetails;
import com.example.metalog.entity.User;
import com.example.metalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//wn
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;	// 별도로 생성해야 함

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다. username = " + username));
        return new CustomUserDetails(user);	// 위에서 생성한 CustomUserDetails Class
    }

    // 필요시 추가
    public UserDetails loadUserByUserId(Long userId) throws IllegalArgumentException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. user_id = " + userId));
        return new CustomUserDetails(user);
    }

    // 필요시 추가

}