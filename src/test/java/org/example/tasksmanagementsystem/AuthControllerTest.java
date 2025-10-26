package org.example.tasksmanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tasksmanagementsystem.controller.AuthController;
import org.example.tasksmanagementsystem.dtos.auth.LoginRequestDto;
import org.example.tasksmanagementsystem.dtos.auth.LoginResponseDto;
import org.example.tasksmanagementsystem.dtos.auth.register.RegisterRequestDto;
import org.example.tasksmanagementsystem.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)  // disabling security filters for testing
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegister_Success() throws Exception {
        RegisterRequestDto request = new RegisterRequestDto();
        request.setName("hazem");
        request.setEmail("hazem@example.com");
        request.setPassword("123456");

        Mockito.doNothing().when(authService).register(any(RegisterRequestDto.class));

        mockMvc.perform(post("/v1/auth/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(jsonPath("$.message").value("You have registered successfully"))
                .andExpect(jsonPath("$.status").value(HttpStatus.CREATED.name()))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CREATED.value()));
    }

}

