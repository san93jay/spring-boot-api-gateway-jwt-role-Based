package com.sanjay.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sanjay.auth.common.Role;
import com.sanjay.auth.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String englishName;

    @Pattern(regexp="\\d+")
    @NotNull
    private String telephone;

    @NotNull
    private String address;

    @NotNull
    private UserRole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto that = (UserDto) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

