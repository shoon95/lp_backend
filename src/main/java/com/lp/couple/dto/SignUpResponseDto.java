package com.lp.couple.dto;

import com.lp.couple.domain.User;
import lombok.Data;

@Data
public class SignUpResponseDto {
    private Long userId;
    private String username;
    private String email;

    public SignUpResponseDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
