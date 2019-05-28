package com.xiuxiuxiu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Component - 零件表
 * 
 * @author 向鹏
 * @date 2019-5-23
 */
@Entity
@Table(name = "component")
public class Component {
	/** 零件id */
	@Id
	@GeneratedValue
	private int id;
	/** 零件名 */
	@Column(nullable = false, unique = true)
	private String name;
	/** 零件价格 */
	@Column(nullable = false)
	private double price;
	/** 零件剩余数量 */
	@Column(nullable = false)
	private int quantity;
	/** 零件类型 */
	@Column(nullable = false)
	private String type;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
