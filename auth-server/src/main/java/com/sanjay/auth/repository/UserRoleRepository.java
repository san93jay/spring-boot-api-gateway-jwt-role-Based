package com.sanjay.auth.repository;

import com.sanjay.auth.common.Role;
import com.sanjay.auth.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRoleName(Role roleName);
}
