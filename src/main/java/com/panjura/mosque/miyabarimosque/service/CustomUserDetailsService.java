package com.panjura.mosque.miyabarimosque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.panjura.mosque.miyabarimosque.bean.User;
import com.panjura.mosque.miyabarimosque.model.CustomUserDetails;
import com.panjura.mosque.miyabarimosque.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired private UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String firstName) {
		Optional<User> usersOptional = usersRepository.findByFirstName(firstName);
			return usersOptional
					.map(CustomUserDetails::new)
					.orElseThrow(()->new UsernameNotFoundException("Username not found!"));
	}
}