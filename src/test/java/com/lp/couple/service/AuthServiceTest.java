package com.lp.couple.service;

import com.lp.couple.domain.User;
import com.lp.couple.dto.JoinRequestDto;
import com.lp.couple.dto.ResponseDto;
import com.lp.couple.dto.SignUpResponseDto;
import com.lp.couple.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void join_ShouldRegisterUser_WhenValidRequest() {

        JoinRequestDto request = new JoinRequestDto();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setConfirmPassword("password");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        ResponseDto<SignUpResponseDto> response = null;
        try {
            response = authService.join(request);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
        // Then
        assertNotNull(response);
        assertEquals("success", response.getStatus());
        assertEquals("create new user success", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("testuser", response.getData().getUsername());
        assertEquals("test@example.com", response.getData().getEmail());

        verify(userRepository).findByEmail(anyString());
        verify(bCryptPasswordEncoder).encode(anyString());
        verify(userRepository).save(any(User.class));
    }
}