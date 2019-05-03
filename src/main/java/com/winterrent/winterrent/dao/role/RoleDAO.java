package com.winterrent.winterrent.dao.role;

import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleName;

import java.util.Optional;

public interface RoleDAO {
    Optional<Role> findByName(RoleName name);
}
