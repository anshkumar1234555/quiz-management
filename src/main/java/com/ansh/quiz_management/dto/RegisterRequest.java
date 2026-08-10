package com.ansh.quiz_management.dto;

import com.ansh.quiz_management.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}