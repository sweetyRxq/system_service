package com.mchain.test.config.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cfg_chaincode")
public class ConfigChaincode implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6007418321917124515L;

	@Id
	private String id;
	
	@Column(name = "name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "channelName", length = 255, nullable = false)
	private String channelName;
	
	@Column(name = "chaincodeName", length = 255, nullable = false)
	private String chaincodeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChaincodeName() {
		return chaincodeName;
	}

	public void setChaincodeName(String chaincodeName) {
		this.chaincodeName = chaincodeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

