package com.example.metalog.controller;



import com.example.metalog.dto.LoginDto;
import com.example.metalog.dto.RegisterDto;
import com.example.metalog.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    // 회원가입
    @PostMapping("/register")
    public Long register(@RequestBody RegisterDto registerDto) {
        return memberService.register(registerDto);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {

        return memberService.login(loginDto);
    }


}
