package com.mchain.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mchain.apollo.client.sdk.entity.LedgerPagination;
import com.mchain.apollo.core.entity.Message;
import com.mchain.test.client.CommentLogSDKClient;
import com.mchain.test.entity.CommentLog;

@RestController
@RequestMapping(value = "/api/blockchain/commentlog")
public class CommentLogBlockChainService {
//GENERATE_START
	private final static String API = "/api/blockchain/commentlog";
	
	@Autowired
	private CommentLogSDKClient commentLogSDKClient;	
	/**
	 * 
	 * @Title: saveCommentLog
	 * @Description: 保存测试业务对象
	 * @param entity
	 * @return
	 * @return: Message<Object>
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<Object> saveCommentLog(@RequestBody CommentLog entity){
		try {
			return commentLogSDKClient.save(entity);
		}catch(Exception e) {
			return new Message<Object>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: updateCommentLog
	 * @Description: 修改测试业务对象
	 * @param entity
	 * @return
	 * @return: Message<Object>
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<Object> updateCommentLog(@RequestBody CommentLog entity){
		try {
			return commentLogSDKClient.update(entity);
		}catch(Exception e) {
			return new Message<Object>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: deleteCommentLog
	 * @Description: 根据ID删除测试业务对象
	 * @param entity
	 * @return
	 * @return: Message<Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Message<Object> deleteCommentLog(@PathVariable String id){
		try {
			return commentLogSDKClient.delete(id);
		}catch(Exception e) {
			return new Message<Object>("500", String.format("POST %s", API), e.getMessage());
		}
	}	
	/**
	 * 
	 * @Title: getCommentLog
	 * @Description: 根据ID获取测试业务对象
	 * @param id
	 * @return
	 * @return: Message<CommentLog>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Message<CommentLog> getCommentLog(@PathVariable String id){
		try {
			return commentLogSDKClient.get(id);
		}catch(Exception e) {
			return new Message<CommentLog>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: QuryAllCommentLog
	 * @Description: 查询所有测试业务对象
	 * @return
	 * @return: Message<List<CommentLog>>
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Message<List<CommentLog>> QuryAllCommentLog(){
		try {
			return commentLogSDKClient.queryAll();
		}catch(Exception e) {
			return new Message<List<CommentLog>>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: QuryCommentLog
	 * @Description: 根据条件查询测试业务对象（列表格式）
	 * @param params
	 * @return
	 * @return: Message<List<CommentLog>>
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<CommentLog>> quryCommentLog(@RequestParam Map<?,?> params){
		try {
			return commentLogSDKClient.query(params);
		}catch(Exception e) {
			return new Message<List<CommentLog>>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: paginateCommentLog
	 * @Description: 根据条件测试业务对象（翻页格式）
	 * @param params
	 * @return
	 * @return: Message<LedgerPagination<CommentLog>>
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<LedgerPagination<CommentLog>> paginateClsTest(@RequestParam Map<?,?> params){
		try {
			return commentLogSDKClient.paginate(params);
		}catch(Exception e) {
			return new Message<LedgerPagination<CommentLog>>("500", String.format("POST %s", API), e.getMessage());
		}
	}
//GENERATE_END

//OPERATE_GENERATE_START    
  
//OPERATE_GENERATE_END

}