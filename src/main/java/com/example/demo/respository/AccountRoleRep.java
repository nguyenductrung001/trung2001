package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Login.AccountRole;
import java.lang.Long;
import java.util.List;

public interface AccountRoleRep extends JpaRepository<AccountRole, Long> {

	List<AccountRole> findByUserId(Long userid);

}
