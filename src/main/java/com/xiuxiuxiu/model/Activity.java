package com.xiuxiuxiu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Activity - （线下维修）活动类
 * 
 * @author 向鹏
 * @date 2019-5-23
 */
@Entity
@Table(name = "repair_activity")
public class Activity {
	/** 设备的 ID 号 */
	@Id
	@GeneratedValue
	private Integer id;

	/** 活动时间 */
	@Column(nullable = false)
	private String time;

	/** 活动地点 */
	@Column(nullable = false)
	private String place;

	/** 定义名为manager_id的外键列，该外键引用manager表的id列 */
	@ManyToOne(targetEntity = Manager.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", nullable = true)
	private Manager manager;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
