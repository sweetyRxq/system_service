package com.mchain.test.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class CommentLog extends PublicInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
//GENERATE_START       
       
	/**
	 * 评论时间
	 */
	private Date commentDate;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 评论人
	 */
	private String commonetUser;
	/**
	 *CommentLog
	 */
	private String dataType = "CommentLog";

	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommonetUser() {
		return commonetUser;
	}
	public void setCommonetUser(String commonetUser) {
		this.commonetUser = commonetUser;
	}
    public String getDataType() {
    	return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

//GENERATE_END
}
