package org.example.tasksmanagementsystem.dtos.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequestDto {
    public String email;
    public String password;
}
