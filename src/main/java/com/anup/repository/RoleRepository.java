package com.anup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.Role;

/**
 *
 * @author Raichand
 */
public interface RoleRepository extends JpaRepository<Role,Integer>{
    
    @Query("SELECT max(r.roleId) FROM Role r")//Retrieving Maximun Id of UserRole Record
   int getMaxRoleId();
    
}
