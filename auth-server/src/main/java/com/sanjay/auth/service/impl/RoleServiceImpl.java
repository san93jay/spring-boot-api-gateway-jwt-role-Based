package com.sanjay.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanjay.auth.dto.RoleDto;
import com.sanjay.auth.entity.UserRole;
import com.sanjay.auth.repository.UserRoleRepository;
import com.sanjay.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserRoleRepository userRoleRepository;
    @Override
    public RoleDto registerRole(RoleDto roleDto) {
       ModelMapper map=new ModelMapper();
       UserRole role= map.map(roleDto, UserRole.class);

       UserRole userRole=userRoleRepository.save(role);
       return map.map(userRole, RoleDto.class);
    }
}
