package com.sanjay.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sanjay.auth.common.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleDto {

    private Long id;

    private Role roleName;


}
