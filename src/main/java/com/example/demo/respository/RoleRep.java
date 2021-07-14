package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Login.Roles;

public interface RoleRep extends JpaRepository<Roles, Long>{

}
