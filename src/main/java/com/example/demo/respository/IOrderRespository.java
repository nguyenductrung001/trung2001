package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Order;

public interface IOrderRespository extends JpaRepository<Order, Long>{

}
