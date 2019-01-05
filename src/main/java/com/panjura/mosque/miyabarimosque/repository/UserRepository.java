package com.panjura.mosque.miyabarimosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panjura.mosque.miyabarimosque.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByFirstName(String username);
	
}
