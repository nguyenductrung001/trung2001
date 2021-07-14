package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Login.Account;
import com.example.demo.model.Login.AccountRole;
import com.example.demo.model.Login.Roles;
import com.example.demo.respository.AccountRep;
import com.example.demo.respository.AccountRoleRep;
import com.example.demo.respository.RoleRep;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AccountRep _accountRepo;
	@Autowired
	AccountRoleRep _accountRoleRepo;
	@Autowired
	RoleRep _roleRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Account> lst = this._accountRepo.findByUser(username);
		Account account;

		if (lst.size() > 0) {
			account = lst.get(0);
		} else {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		System.out.println("Found User: " + account);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = new ArrayList<String>();
//		List<Account> lst =_accountRepo.findByUserName(userName);
//		if( lst.size()>0) {
//		Account a = lst.get(0);
		for (AccountRole ur : this._accountRoleRepo.findByUserId(account.getId())) {
			Optional<Roles> opt = this._roleRepo.findById(ur.getRoleId());
			if (opt.isPresent())
				roleNames.add(opt.get().getName());
		}

//		}

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames.size() > 0) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(account.getUser(), //
				account.getEncrytedPassword(), grantList);

		return userDetails;
	}

}
