package com.mchain.test.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class Topic extends PublicInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
//GENERATE_START       
       
	/**
	 * 主题名称：
	 */
	private String topicName;
	/**
	 * 详细日志
	 */
	private List<CommentLog> commentLogs;
	/**
	 *Topic
	 */
	private String dataType = "Topic";

	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public List<CommentLog> getCommentLogs() {
		return commentLogs;
	}
	public void setCommentLogs(List<CommentLog> commentLogs) {
		this.commentLogs = commentLogs;
	}
    public String getDataType() {
    	return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

//GENERATE_END
}
