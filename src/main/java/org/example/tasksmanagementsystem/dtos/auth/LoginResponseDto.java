package org.example.tasksmanagementsystem.dtos.auth;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponseDto {
    public static String tokenType = "Bearer";  //static remove
    public String accessToken;
    public LoginResponseDto(String accessToken) { this.accessToken = accessToken; }
}
