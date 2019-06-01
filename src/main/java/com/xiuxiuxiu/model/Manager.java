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
 * Manager - 管理员类
 */
@Entity
@Table(name = "manager")
@JsonIgnoreProperties(value = {"articleList","hibernateLazyInitializer", "handler"})
public class Manager {

	/**
	 * 管理员的id
	 */
	@Id
	@GeneratedValue
	protected Integer id;
	/**
	 * 管理员密码
	 */
	@Column(nullable = false)
	protected String password;
	/**
	 * 管理员的id
	 */
	
	protected String name;
	/**
	 * 管理员的联系方式（电话号码）
	 */
	protected String phoneNumber;
	/**
	 * 管理员的权限等级
	 */
	@Column(nullable = false)
	protected Integer accessLevel;
	/**
	 * 管理员的住址
	 */
	protected String address;
	/**
	 * 管理员的邮箱
	 */
	protected String email;
	/**
	 * 管理员发布的文章列表 定义该Manager实体所有关联的Article实体； 指定mappedBy属性表明该Manager实体不控制关联关系
	 */
	@OneToMany(targetEntity = Article.class, mappedBy = "manager")
	private List<Article> articleList;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

}
