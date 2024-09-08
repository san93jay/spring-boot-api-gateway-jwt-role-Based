package com.sanjay.authservice.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenDto {
    private String accessToken;
}
