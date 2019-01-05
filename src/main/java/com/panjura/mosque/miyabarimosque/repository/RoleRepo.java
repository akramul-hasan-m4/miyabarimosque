/**
 * 
 */
package com.panjura.mosque.miyabarimosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panjura.mosque.miyabarimosque.bean.Role;

/**
 * @author DOLPHIN
 *
 */
public interface RoleRepo extends JpaRepository<Role, Integer> {

	Role findByRoleId (Integer id);
	Role findByRoleName (String name);
}
