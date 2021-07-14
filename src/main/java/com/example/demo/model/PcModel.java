package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Table(name = "PC")
@SQLDelete(sql = "UPDATE PC SET is_deleted = 1 WHERE pc_id=?")
@Where(clause = "is_deleted =0")
@Entity
@Data
public class PcModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PcId")
	private Long pcId;
	@SuppressWarnings("deprecation")
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	@Column(name = "image", nullable = false)
	private String image;
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	@Column(name = "date", nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
	private Date date;
	@Column(name = "Quantity", nullable = false)
	private Integer quantity;
	@Column(name = "Author", length = 100, nullable = false)
	private String author;
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private PcType type;
	@Column(name = "IsDeleted", nullable = false)
	private boolean isDeleted;
	@OneToMany(mappedBy = "pc")
	private List<OrderDetail> orderDetails;

	public static Object findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
