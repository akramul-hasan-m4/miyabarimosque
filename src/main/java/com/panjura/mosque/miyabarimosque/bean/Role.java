package com.panjura.mosque.miyabarimosque.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="role_id")
	private Integer roleId;

	@Column
	//@NaturalId
	private String roleName;

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<>();

}
