package com.xiuxiuxiu.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ReservationImgUrl - 预约单图片文件地址类
 */
@Entity
@Table(name = "reservation_img_url")
public class ReservationImgUrl {
	
	
	/** 预约单图片文件ID 号 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * 对应的预约单；
	 *  定义名为reservation_id的外键列；该外键引用reservation表的主键(id)列,采用懒加载
	 */
	@JsonIgnoreProperties
	@ManyToOne(targetEntity = Reservation.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;
	/**图片的URL地址*/
	private String img_url;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

}
