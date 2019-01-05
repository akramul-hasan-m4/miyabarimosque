package com.panjura.mosque.miyabarimosque.serviceimpl;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.panjura.mosque.miyabarimosque.bean.User;
import com.panjura.mosque.miyabarimosque.repository.UserRepository;
import com.panjura.mosque.miyabarimosque.service.GenericsDAOService;

public class UserServiceImpl implements GenericsDAOService<User> {

	@Autowired private UserRepository userRepository;

	@Override
	@Transactional
	public List<User> getAllData() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<User> findById(Integer id) {
		return Optional.of(userRepository.getOne(id));
	}

	@Override
	@Transactional
	public void saveOrUpdateData(User obj) {
		userRepository.save(obj);
	}

	@Override
	@Transactional
	public void deleteData(Integer id) {
		userRepository.deleteById(id);
	}
}
