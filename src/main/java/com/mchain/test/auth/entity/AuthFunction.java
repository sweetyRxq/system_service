package com.mchain.test.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_function")
public class AuthFunction implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = 4854034949664795102L;
	
	/**
	 * 功能编码
	 */
	@Id
	private String code;
	/**
	 * 功能名称
	 */
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	/**
	 * 功能类型
	 */
	@Column(name = "type", length = 20, nullable = false)
	private String type;
	
	@Column(name = "category", length = 50, nullable = true)
	private String category;
	/**
	 * 功能类型
	 */
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}

