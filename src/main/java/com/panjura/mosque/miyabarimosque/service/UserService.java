package com.panjura.mosque.miyabarimosque.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panjura.mosque.miyabarimosque.bean.User;

@Service
public interface UserService {

	public List<User> getAllUser();
	
	public User findByUserId(Integer userId);
	
	public void saveUser(User user);
	
	public void deleteuser(Integer id);
}
