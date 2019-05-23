package com.xiuxiuxiu.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Equipment - 设备类
 * 
 * @author 向鹏
 * @date 2019-5-23
 */
@Entity
@Table(name = "equipment")
public class Equipment {
	
	/** 设备的 ID 号 */
	@Id
	@GeneratedValue
	private Integer id;

	/** 设备的名称 */
	private String equipmentName;

	/** 定义名为user_id的外键列，该外键引用student表的主键(id)列,采用懒加载 */
	@ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Student student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
