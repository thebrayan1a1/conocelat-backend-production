package com.example.conocemelat.service;

import com.example.conocemelat.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> listAllRoles();

    public Role saveRole(Role user);

    public Role getRoleById(Long id);

    public void deleteRole(Long id);

}
