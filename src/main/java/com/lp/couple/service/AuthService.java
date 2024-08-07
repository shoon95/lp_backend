package com.lp.couple.service;

import com.lp.couple.domain.User;
import com.lp.couple.dto.JoinRequestDto;
import com.lp.couple.dto.ResponseDto;
import com.lp.couple.dto.SignUpResponseDto;
import com.lp.couple.exception.authException.EmailAlreadyExistsException;
import com.lp.couple.exception.authException.PasswordMismatchException;
import com.lp.couple.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public ResponseDto<SignUpResponseDto> join(JoinRequestDto joinRequestDto) throws Exception {
        log.info("회원가입 시도 : " + joinRequestDto.getEmail());

        Optional<User> existingUser = userRepository.findByEmail(joinRequestDto.getEmail());
        if (existingUser.isPresent()) {
            log.info("존재하는 이메일로 가입 시도 : " + joinRequestDto.getEmail());
            throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.");
        }

        if (!joinRequestDto.getPassword().equals(joinRequestDto.getConfirmPassword())) {
            log.info("비밀번호 검증 실패: " + joinRequestDto.getEmail());
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        // 검증 통과 시 User 생성
        User user = User.builder()
                .username(joinRequestDto.getUsername())
                .email(joinRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(joinRequestDto.getPassword()))
                .build();

        userRepository.save(user);

        log.info("유저 등록 성공 : " + user.getEmail());
        
        return ResponseDto.success(new SignUpResponseDto(user), "create new user success");
    }
}
