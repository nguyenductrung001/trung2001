package com.example.demo.model.Login;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class AccountRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long roleId;
	@ManyToOne
	@JoinColumn(name = "userId", updatable = false, insertable = false)
	@ToString.Exclude
	Account account;
	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "roleId", updatable = false, insertable = false)
	Roles roles;
}
