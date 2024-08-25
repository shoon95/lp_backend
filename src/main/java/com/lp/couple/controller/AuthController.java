package com.lp.couple.controller;

import com.lp.couple.dto.JoinRequestDto;
import com.lp.couple.dto.ResponseDto;
import com.lp.couple.dto.SignUpResponseDto;
import com.lp.couple.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<String> getMain() {
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto<SignUpResponseDto>> join(@RequestBody JoinRequestDto joinRequestDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.join(joinRequestDto));
    }
}
