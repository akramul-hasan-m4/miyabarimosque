package com.panjura.mosque.miyabarimosque.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SocietyMember {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="member_id")
	private Integer memberId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String fathersName;
	
	@Column
	private Integer noOfFamilyMember;
	
	@Column
	private Integer noOfFamilyEarningPerson;
	
	@Column
	private String mainEarningPerson;
	
	@Column
	private BigDecimal familyMonthlyIncome;
	
	@Column
	private String phone;
	
	@Column
	private String currentAddress;

	@Column
	private String permanentAddress;
	
	@Column
	private String status;
	
	@Column
	private Boolean approvedAsUser;

}
