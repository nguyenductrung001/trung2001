package com.example.demo.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.OrderDetail;
import com.example.demo.model.OrderDetailId;

public interface IOrderDetailRepository extends CrudRepository<OrderDetail, OrderDetailId>{
//@Query(value ="SELECT * FROM  order_detail")
	
}
