package com.example.demo.model;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class CartDetail {
 private String name;
 private Integer quantity;
 private BigDecimal price;
 private String  image;
}
