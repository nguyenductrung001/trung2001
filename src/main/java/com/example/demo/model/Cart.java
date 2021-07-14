package com.example.demo.model;

import java.util.Map;

import lombok.Data;

@Data
public class Cart {
	private Map<Long, CartDetail> cartDetails;
}
