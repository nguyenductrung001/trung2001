package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.Cart;
import com.example.demo.model.CartDetail;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.PcModel;
import com.example.demo.service.OrderService;
import com.example.demo.service.PcService;
import com.example.demo.utils.CartUtils;
import com.example.demo.utils.CommonConst;

import lombok.Data;

@Controller
@RequestMapping("/shop")
public class ShopViewController {

	@Autowired
	private PcService _pcService;
	@Autowired
	private OrderService _orderService;
	
	@RequestMapping("/addToCart/{pcId}")
//	/@PathVariable lấy đc id cần thêm
	public String addToCart(@PathVariable Long pcId, Model model, HttpServletRequest request) {
		Cart cart = CartUtils.getCartInSession(request);
		CartDetail cartDetail;
		PcModel pcModel = new PcModel();
		if(cart.getCartDetails().get(pcId) == null) {
			cartDetail = new CartDetail();
			pcModel = _pcService.getById(pcId);
			cartDetail.setName(pcModel.getName());
			cartDetail.setPrice(pcModel.getPrice());
			cartDetail.setImage(pcModel.getImage());
			cartDetail.setQuantity(1);
			
		}else {
			cartDetail = cart.getCartDetails().get(pcId);
			cartDetail.setQuantity(cartDetail.getQuantity() +1);
			
		}
		
		cart.getCartDetails().put(pcId, cartDetail);
		model.addAttribute("cartDetails", cart.getCartDetails());
		return "redirect:/pc/home";
	}

	@RequestMapping("/cart")
	public String viewCart(Model model, HttpServletRequest request) {
		// lấy thông tin của cart add vào model
		model.addAttribute("cartDetails", CartUtils.getCartInSession(request).getCartDetails());
		return "shop/cart";

	}

	/* @PostMapping("/buy") */
	@RequestMapping("/buy")
	public String buy(HttpServletRequest request, Model model) throws Exception {
		Cart cart = CartUtils.getCartInSession(request);
		Order order = new Order();
		// Đưa thông tin cartDetail về Order
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		cart.getCartDetails().forEach((pcId, cartDetail) -> {
			PcModel pc = _pcService.getById(pcId);
			OrderDetail detail = OrderDetail.builder().pc(pc) // sản phẩm
					.quantity(cartDetail.getQuantity()) // số lượng
					.build();
			details.add(detail);
		});

		order.setOrderDetails(details);
		// Thêm order
		_orderService.newOrder(order);

		BigDecimal price = new BigDecimal(0);
		for (OrderDetail orderDetail : details) {
			price = price.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
		}
		CartUtils.removeCartInSession(request);
		model.addAttribute("order", order);
		model.addAttribute("sumPrice", price);
		
		return "shop/order";
	} 
	
}

