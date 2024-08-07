package com.lp.couple.dto;

import lombok.Data;

@Data
public class JoinRequestDto {

    private String email;
    private String username;
    private String password;
    private String confirmPassword;

}