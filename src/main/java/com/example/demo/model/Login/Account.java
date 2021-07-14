package com.example.demo.model.Login;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column( length = 100, nullable = false,unique = true)
	private String user;
	@Column()
	private String  encrytedPassword;
	@Column( nullable = false)
	private boolean active;
	@OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
	@ToString.Exclude
	List<AccountRole> accountRoles;
}
