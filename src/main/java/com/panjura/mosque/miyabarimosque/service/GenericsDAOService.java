package com.panjura.mosque.miyabarimosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface GenericsDAOService<T> {

	public List<T> getAllData();
	
	public Optional<T> findById(Integer id);
	
	public void saveOrUpdateData(T obj);
	
	public void deleteData(Integer id);
}

