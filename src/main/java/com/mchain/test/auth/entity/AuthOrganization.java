package com.mchain.test.auth.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "auth_organization")
public class AuthOrganization implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1832124431516233030L;
	
	/**
	 * 部门编码
	 */
	@Id
	private String code;
	/**
	 * 部门名称
	 */
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	/**
	 * 部门描述
	 */
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	/**
	 * 网络组织机构名称
	 */
	@Column(name = "networkOrgName", length = 100, nullable = true)
	private String networkOrgName;
	/**
	 * SDK访问地址
	 */
	@Column(name = "sdkUrl", length = 500, nullable = true)
	private String sdkUrl;
	/**
	 * SDK应用访问ID
	 */
	@Column(name = "sdkAppId", length = 100, nullable = true)
	private String sdkAppId;
	/**
	 * SDK应用访问密钥
	 */
	@Column(name = "sdkAppSecret", length = 100, nullable = true)
	private String sdkAppSecret;
	/**
	 * 是否激活
	 */
	@Column(name = "active", nullable = true)
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
	public String getNetworkOrgName() {
		return networkOrgName;
	}
	public void setNetworkOrgName(String networkOrgName) {
		this.networkOrgName = networkOrgName;
	}
	public String getSdkUrl() {
		return sdkUrl;
	}
	public void setSdkUrl(String sdkUrl) {
		this.sdkUrl = sdkUrl;
	}
	public String getSdkAppId() {
		return sdkAppId;
	}
	public void setSdkAppId(String sdkAppId) {
		this.sdkAppId = sdkAppId;
	}
	public String getSdkAppSecret() {
		return sdkAppSecret;
	}
	public void setSdkAppSecret(String sdkAppSecret) {
		this.sdkAppSecret = sdkAppSecret;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
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

}

