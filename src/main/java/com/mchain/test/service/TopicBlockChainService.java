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
import com.mchain.test.client.TopicSDKClient;
import com.mchain.test.entity.Topic;

@RestController
@RequestMapping(value = "/api/blockchain/topic")
public class TopicBlockChainService {
//GENERATE_START
	private final static String API = "/api/blockchain/topic";
	
	@Autowired
	private TopicSDKClient topicSDKClient;	
	/**
	 * 
	 * @Title: saveTopic
	 * @Description: 保存测试业务对象
	 * @param entity
	 * @return
	 * @return: Message<Object>
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<Object> saveTopic(@RequestBody Topic entity){
		try {
			return topicSDKClient.save(entity);
		}catch(Exception e) {
			return new Message<Object>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: updateTopic
	 * @Description: 修改测试业务对象
	 * @param entity
	 * @return
	 * @return: Message<Object>
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<Object> updateTopic(@RequestBody Topic entity){
		try {
			return topicSDKClient.update(entity);
		}catch(Exception e) {
			return new Message<Object>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: deleteTopic
	 * @Description: 根据ID删除测试业务对象
	 * @param entity
	 * @return
	 * @return: Message<Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Message<Object> deleteTopic(@PathVariable String id){
		try {
			return topicSDKClient.delete(id);
		}catch(Exception e) {
			return new Message<Object>("500", String.format("POST %s", API), e.getMessage());
		}
	}	
	/**
	 * 
	 * @Title: getTopic
	 * @Description: 根据ID获取测试业务对象
	 * @param id
	 * @return
	 * @return: Message<Topic>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Message<Topic> getTopic(@PathVariable String id){
		try {
			return topicSDKClient.get(id);
		}catch(Exception e) {
			return new Message<Topic>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: QuryAllTopic
	 * @Description: 查询所有测试业务对象
	 * @return
	 * @return: Message<List<Topic>>
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Message<List<Topic>> QuryAllTopic(){
		try {
			return topicSDKClient.queryAll();
		}catch(Exception e) {
			return new Message<List<Topic>>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: QuryTopic
	 * @Description: 根据条件查询测试业务对象（列表格式）
	 * @param params
	 * @return
	 * @return: Message<List<Topic>>
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<Topic>> quryTopic(@RequestParam Map<?,?> params){
		try {
			return topicSDKClient.query(params);
		}catch(Exception e) {
			return new Message<List<Topic>>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: paginateTopic
	 * @Description: 根据条件测试业务对象（翻页格式）
	 * @param params
	 * @return
	 * @return: Message<LedgerPagination<Topic>>
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<LedgerPagination<Topic>> paginateClsTest(@RequestParam Map<?,?> params){
		try {
			return topicSDKClient.paginate(params);
		}catch(Exception e) {
			return new Message<LedgerPagination<Topic>>("500", String.format("POST %s", API), e.getMessage());
		}
	}
//GENERATE_END

//OPERATE_GENERATE_START    
//deleteById_Generate_Start


  public Integer deleteById(String id) {
 	Integer object = null;
    
    //deleteById_User_Start 
    //deleteById_User_End

    return object;
  }
  

//deleteById_Generate_End
    
  
//OPERATE_GENERATE_END

}
