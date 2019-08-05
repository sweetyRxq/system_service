package com.mchain.test.auth.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "auth_user")
public class AuthUser implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = -6473834450619995236L;
	/**
	 * 用户ID
	 */
	@Id
	private String id;
	/**
	 * 用户账号
	 */
	@Column(name = "account", length = 255, nullable = false)
	private String account;
	/**
	 * 用户电子邮箱
	 */
	@Column(name = "email", length = 255, nullable = false)
	private String email;
	/**
	 * 用户密码
	 */
	@Column(name = "password", length = 500, nullable = false)
	private String password;
	/**
	 * 是否激活
	 */
	@Column(name = "active", nullable = false)
	private Integer active = 1;
	/**
	 * 创建时间
	 */
	@Column(name = "createdAt", nullable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	/**
	 * 修改时间
	 */
	@Column(name = "updatedAt", nullable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedAt;
	/**
	 * 用户角色
	 */
	@ManyToOne
	@JoinColumn(name = "roleCode")
	private AuthRole role;
	/**
	 * 用户所属部门
	 */
	@ManyToOne
	@JoinColumn(name = "orgCode")
	private AuthOrganization organization;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public AuthRole getRole() {
		return role;
	}

	public void setRole(AuthRole role) {
		this.role = role;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public AuthOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(AuthOrganization organization) {
		this.organization = organization;
	}

	
}

