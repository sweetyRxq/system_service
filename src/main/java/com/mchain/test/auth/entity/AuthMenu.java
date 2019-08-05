package com.mchain.test.auth.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "auth_menu")
public class AuthMenu implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: serialVersionUID
	 */
	private static final long serialVersionUID = -1484510775171476881L;
	/**
	 * 菜单编码
	 */
	@Id
	private String code;
	/**
	 * 菜单名称
	 */
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	/**
	 * 菜单描述
	 */
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	/**
	 * 图标
	 */
	@Column(name = "icon", length = 255, nullable = true)
	private String icon;
	/**
	 * 页面
	 */
	@Column(name = "url", length = 500, nullable = true)
	private String url;
	/**
	 * 参数
	 */
	@Column(name = "params", length = 500, nullable = true)
	private String params;
	/**
	 * 排序（升序）
	 */
	@Column(name = "sortIndex")
	private Integer sortIndex;
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
	 * 所属功能
	 */
	@ManyToOne
	@JoinColumn(name = "functionCode")
	private AuthFunction function;
	
	@Column(name = "parentCode", length = 50, nullable = true)
	private String parentCode;
	
	/**
	 * 子菜单
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentCode", nullable = true)
	@OrderBy("sortIndex")
	private List<AuthMenu> children;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
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

	public AuthFunction getFunction() {
		return function;
	}

	public void setFunction(AuthFunction function) {
		this.function = function;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public List<AuthMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AuthMenu> children) {
		this.children = children;
	}

}

