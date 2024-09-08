package com.sanjay.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
