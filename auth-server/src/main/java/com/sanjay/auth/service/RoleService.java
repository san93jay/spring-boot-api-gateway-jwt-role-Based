package com.sanjay.auth.service;

import com.sanjay.auth.dto.RoleDto;
import com.sanjay.auth.dto.UserDto;

public interface RoleService {
    RoleDto registerRole(RoleDto roleDto);
}
