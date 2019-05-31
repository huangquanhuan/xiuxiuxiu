package com.xiuxiuxiu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Student - 学生类
 * 
 * @author 向鹏
 * @date 2019-5-23
 */
@Entity
@Table(name = "student")
@JsonIgnoreProperties(value = {"equipmentList","reservationList"})
public class Student {

	/** 用户的id */
	@Id
	@GeneratedValue
	protected Integer id;
	
	/** 用户密码 */
	@Column(nullable = false)
	protected String password;
	
	/** 用户名 */
	protected String name;
	
	/** 用户的联系方式（电话号码） */
	@Column(nullable = false, unique = true)
	protected String phoneNumber;
	
	/** 用户的权限等级 */
	@Column(nullable = false)
	protected Integer accessLevel;
	
	/** 用户的住址 */
	protected String address;
	
	/** 用户的邮箱 */
	protected String email;
	
	/** 用户的学号 */
	@Column(unique = true)
	private String studentId;
	
	/**
	 * 用户拥有的设备列表； 定义该Student实体所有关联的Equipment实体； 指定mappedBy属性表明该Student实体不控制关联关系
	 */
	@OneToMany(targetEntity = Equipment.class, mappedBy = "student")
	private List<Equipment> equipmentList;
	
	/**
	 * 用户拥有的预约单列表； 定义该Student实体所有关联的Reservation实体； 指定mappedBy属性表明该Student实体不控制关联关系
	 */
	@OneToMany(targetEntity = Reservation.class, mappedBy = "student")
	public List<Reservation> reservationList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(Integer accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", studentId=" + studentId + "]";
	}
	
	
}
