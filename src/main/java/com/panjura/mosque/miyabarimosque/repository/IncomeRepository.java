package com.panjura.mosque.miyabarimosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panjura.mosque.miyabarimosque.bean.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
