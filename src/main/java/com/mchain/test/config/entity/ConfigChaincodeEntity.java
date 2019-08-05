package com.mchain.test.config.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cfg_chaincode_entity")
public class ConfigChaincodeEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6024137569125994614L;
	/**
	 * 智能合约业务名称
	 */
	@Id
	private String name;
	/**
	 * 智能合约业务别名
	 */
	@Column(name = "alias", length = 255, nullable = true)
	private String alias;
	/**
	 * 智能合约业务对应的配置信息
	 */
	@ManyToOne
	@JoinColumn(name = "chaincodeId")
	private ConfigChaincode chaincode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public ConfigChaincode getChaincode() {
		return chaincode;
	}
	public void setChaincode(ConfigChaincode chaincode) {
		this.chaincode = chaincode;
	}

}

