package com.lp.couple.service;

import com.lp.couple.domain.User;
import com.lp.couple.dto.JoinRequestDto;
import com.lp.couple.repository.UserRepository;
import com.lp.couple.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> join(JoinRequestDto joinRequestDto) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(joinRequestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("커스텀 에러 추가 이미 이메일 존재");
        }

        if (!joinRequestDto.getPassword().equals(joinRequestDto.getConfirmPassword())) {
            throw new Exception("커스텀 에러 추가 비밀번호 일치하지 않음");
        }

        User newUser = new User();
        newUser.setEmail(joinRequestDto.getEmail());
        newUser.setPassword(passwordEncoder.hashPassword(joinRequestDto.getPassword()));

        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }
}
