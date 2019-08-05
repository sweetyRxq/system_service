package com.mchain.test.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class PublicInfo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
//GENERATE_START       
       
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 主键id：主键
	 */
	private String uuid;
	/**
	 *PublicInfo
	 */
	private String dataType = "PublicInfo";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
    public String getDataType() {
    	return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

//GENERATE_END
}
