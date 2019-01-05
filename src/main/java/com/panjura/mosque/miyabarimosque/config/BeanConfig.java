package com.panjura.mosque.miyabarimosque.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.panjura.mosque.miyabarimosque.bean.Income;
import com.panjura.mosque.miyabarimosque.bean.User;
import com.panjura.mosque.miyabarimosque.service.GenericsDAOService;
import com.panjura.mosque.miyabarimosque.serviceimpl.IncomeServiceImpl;
import com.panjura.mosque.miyabarimosque.serviceimpl.UserServiceImpl;

@Configuration
public class BeanConfig {

	@Bean
	@Qualifier("userService")
	public GenericsDAOService<User> userServiceImpl() {
		return new UserServiceImpl();
	}
	
	@Bean
	@Qualifier("incomeService")
	public GenericsDAOService<Income> incomeServiceImpl() {
		return new IncomeServiceImpl();
	}
}
