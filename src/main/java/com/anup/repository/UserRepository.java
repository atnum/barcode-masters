package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.User;

/**
 *
 * @author Raichand
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	@Query("SELECT max(u.userId) FROM User u") // Retrieving Maximun Id of User Record
	int getMaxUserId();

	@Query("SELECT u.userId,u.firstName,u.lastName,u.username,u.email,u.password,u.enabled,r.role from User u,Role r WHERE u.userId = r.user") // Retrieving
																																				// All
																																				// User
																																				// Records

	List<User> getAllUser();

}
