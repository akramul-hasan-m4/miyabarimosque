package com.panjura.mosque.miyabarimosque.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "income")
@Data
public class Income implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="income_id")
	private Integer incomeId;
	
	@Column(name ="date")
	private LocalDate date;
	
	@Column(name ="incomeType")
	private IncomeType incomeType;
	
	@Column(name ="amount")
	private BigDecimal amount;
	
	@Column(name ="incomeDescription")
	private String incomeDescription;
	
	public String getIncomeType() {
		return incomeType.label();
	}
	
}
