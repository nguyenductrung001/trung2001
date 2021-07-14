package com.example.demo.utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.Cart;
import com.example.demo.model.CartDetail;

public class CartUtils {
	// Products in the cart, stored in Session.
	// getCartInSession lấy ra các thông tin của cart
		public static Cart getCartInSession(HttpServletRequest request) {
			// request lấy ra các thông tin ở trong session.
			Cart cart = (Cart) request.getSession().getAttribute("myCart");
		
			if (cart == null) {
				cart = new Cart();
				cart.setCartDetails(new HashMap<Long, CartDetail>());
				request.getSession().setAttribute("myCart", cart);
			}
			return cart;
		}
		
		public static void removeCartInSession(HttpServletRequest request) {
			request.getSession().removeAttribute("myCart");
		}
}
