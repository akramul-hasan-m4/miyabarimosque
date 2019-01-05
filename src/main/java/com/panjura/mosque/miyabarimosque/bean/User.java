package com.panjura.mosque.miyabarimosque.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="user_id")
	private Integer userId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String fathersName;

	@Column
	private String phone;

	@Column
	private String password;

	@Column
	private String currentAddress;

	@Column
	private String permanentAddress;

	@Column
	private String status;

	@Column
	private String securityQuestion;

	@Column
	private String securityAns;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private List<Role> roles = new ArrayList<>();
	
	public User(User users) {
		this.userId = users.getUserId();
		this.firstName = users.getFirstName();
		this.lastName = users.getLastName();
		this.fathersName = users.getLastName();
		this.phone = users.getPhone();
		this.password = users.getPassword();
		this.roles = users.getRoles();
		this.currentAddress = users.getCurrentAddress();
		this.permanentAddress = users.getPermanentAddress();
		this.status = users.getStatus();
		this.securityQuestion = users.getSecurityQuestion();
		this.securityAns = users.getSecurityAns();
	}
	
}
