package com.anup.service;

import java.util.List;

import com.anup.entity.Role; 

/**
 *
 * @author Raichand
 */
public interface RoleService {
    
    public Role create(Role userrole);
   
    public List<Role> findAll();
   
    public Role findById(Integer userRoleId);
    public  int CreateNewRoleId();
    
}
