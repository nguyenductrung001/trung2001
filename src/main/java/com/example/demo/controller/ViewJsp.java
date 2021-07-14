package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cart;
import com.example.demo.utils.CartUtils;

@Controller
public class ViewJsp {
	@RequestMapping("/header")
	public String header(Model model, HttpServletRequest request) {
		Cart cart = CartUtils.getCartInSession(request);
		Integer carcount = 0 ;
		for (Long ig : cart.getCartDetails().keySet()) {
			carcount += cart.getCartDetails().get(ig).getQuantity();
		}
		model.addAttribute("carcount", carcount );
		return "pc/header";
	}

	@RequestMapping("/footer")
	public String footer() {
		return "pc/footer";
	}

}
