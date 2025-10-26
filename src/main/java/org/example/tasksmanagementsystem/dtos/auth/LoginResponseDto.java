package org.example.tasksmanagementsystem.dtos.auth;


public class LoginResponseDto {
    public static String tokenType = "Bearer";  //static remove
    public String accessToken;
    public LoginResponseDto(String accessToken) { this.accessToken = accessToken; }
}
