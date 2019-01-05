package com.panjura.mosque.miyabarimosque.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.panjura.mosque.miyabarimosque.bean.Income;
import com.panjura.mosque.miyabarimosque.repository.IncomeRepository;
import com.panjura.mosque.miyabarimosque.service.GenericsDAOService;

public class IncomeServiceImpl implements GenericsDAOService<Income>{

	@Autowired private IncomeRepository incomeRepo;
	
	@Override
	@Transactional
	public List<Income> getAllData() {
		return incomeRepo.findAll();
	}

	@Override
	@Transactional
	public Optional<Income> findById(Integer id) {
		return Optional.of(incomeRepo.getOne(id));
	}

	@Override
	@Transactional
	public void saveOrUpdateData(Income obj) {
		incomeRepo.save(obj);
	}

	@Override
	@Transactional
	public void deleteData(Integer id) {
		incomeRepo.deleteById(id);
	}

}
