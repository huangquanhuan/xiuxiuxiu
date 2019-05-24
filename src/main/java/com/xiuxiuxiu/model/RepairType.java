package com.xiuxiuxiu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//待修改
/**
 * RepairType - 维修类型类
 * 
 * @author 黄权焕
 * @date 2019-5-23
 */
@Entity
@Table(name = "RepairType")
public class RepairType {

	/** 维修问题id */
	@Id
	@GeneratedValue
	private int id;
	/** 维修类型: */
	@Column(nullable = false, unique = true)
	private String Type;
}
