package com.example.demo.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "[Order]")
public class Order {

	@Id
	@GeneratedValue
	private Long orderId;
	
	@Column(name = "Date",nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
	private Date createdDate = Calendar.getInstance().getTime();
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
}
