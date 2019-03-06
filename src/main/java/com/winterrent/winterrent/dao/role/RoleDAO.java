package com.winterrent.winterrent.dao.role;

import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleType;

public interface RoleDAO {
    Role findByRole(RoleType roleType);
}
