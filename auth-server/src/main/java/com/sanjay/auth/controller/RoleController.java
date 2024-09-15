package com.sanjay.auth.controller;

import com.sanjay.auth.dto.RoleDto;
import com.sanjay.auth.dto.UserDto;
import com.sanjay.auth.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    @PostMapping(path = "/register")
    public RoleDto registerRole(@Valid @RequestBody RoleDto roleDto) {
        return roleService.registerRole(roleDto);
    }
}
