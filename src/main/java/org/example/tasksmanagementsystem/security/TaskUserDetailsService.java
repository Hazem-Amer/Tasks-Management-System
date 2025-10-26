package org.example.tasksmanagementsystem.security;

import lombok.AllArgsConstructor;
import org.example.tasksmanagementsystem.model.enums.Authorities;
import org.example.tasksmanagementsystem.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        org.example.tasksmanagementsystem.model.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Authorities.USER.name())
                .build();
    }
}
