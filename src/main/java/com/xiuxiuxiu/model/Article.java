package com.xiuxiuxiu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Article - 文章类
 * 
 * @author 向鹏
 * @date 2019-5-23
 */
@Entity
@Table(name = "article")

public class Article {

	/** 文章id */
	@Id
	@GeneratedValue
	private int id;

	/** 发布该文章的管理员；定义名为user_id的外键列，该外键引用manager表的主键(id)列 */
	@ManyToOne(targetEntity = Manager.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
	private Manager manager;

	/** 文章标题 */
	@Column(nullable = false, unique = true)
	private String title;

	/** 文章内容 */
	private String text;

	/** 文章发布时间 格式为2019-5-3 09:28:30 */
	@Column(nullable = false)
	private String time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
