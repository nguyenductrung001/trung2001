package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
	//@Embeddable để đánh dấu class này sử dụng là Id.
	@EmbeddedId
	private OrderDetailId id;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "orderId", insertable = false, updatable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "pcId", insertable = false, updatable = false)
	private PcModel pc;
}
