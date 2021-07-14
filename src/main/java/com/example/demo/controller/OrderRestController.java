package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.PcModel;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderRestController {

	@Autowired
	private OrderService _orderService;
	
	@PostMapping
	public int newOrder() {
		try {
			PcModel pc = new PcModel();
			pc.setPcId((long) 67);
			Order order = new Order();
			List<OrderDetail> details = new ArrayList<OrderDetail>();
			details.add(OrderDetail.builder()
					.pc(pc).quantity(100).build());
			order.setOrderDetails(details);
			_orderService.newOrder(order);
			
			return 1;
		}
		catch (Exception ex) {
			return 0;
		}
	}
	
	@GetMapping()
	public Iterable<Order> getOrders() {
		return _orderService.getAll();
	}
}
