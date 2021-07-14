package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Login.Account;

public interface AccountRep extends JpaRepository<Account, Long> {

	List<Account> findByUser(String user);

}
