package com.lp.couple.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @jakarta.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
//    private String username;
//    private String nickname;
    private String password;
    private String email;
//    private String phoneNumber;
//    private boolean isVerified;
//    private String partnerCode;
}
