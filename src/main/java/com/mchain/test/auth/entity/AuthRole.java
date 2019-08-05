package com.mchain.test.auth.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "auth_role")
public class AuthRole implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = -5498391285569504069L;
	
	/**
	 * 角色编码
	 */
	@Id
	private String code;
	/**
	 * 角色名称
	 */
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	/**
	 * 角色描述
	 */
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	/**
	 * 角色功能权限
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "auth_role_function", joinColumns = {@JoinColumn(name = "role_code", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "function_code", nullable = false, updatable = false)})
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private List<AuthFunction> functions;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AuthFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(List<AuthFunction> functions) {
		this.functions = functions;
	}

}

