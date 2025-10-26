package org.example.tasksmanagementsystem.dtos.auth.register;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequestDto {
    public String email;
    public String password;
    public String name;
}
