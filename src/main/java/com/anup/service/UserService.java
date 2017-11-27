package com.anup.service;

import java.util.List;

import com.anup.entity.User;

/**
 *
 * @author Raichand
 */
public interface UserService {

	public User create(User user);

	public List<User> findAll();

	public User findById(Integer id);

	public  int CreateNewUserId();

}
