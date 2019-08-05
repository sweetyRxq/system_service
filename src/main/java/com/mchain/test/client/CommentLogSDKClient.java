package com.mchain.test.client;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mchain.apollo.client.sdk.DirectSDKClient;
import com.mchain.apollo.client.sdk.entity.LedgerPagination;
import com.mchain.apollo.client.sdk.entity.LedgerQuerySpecification;
import com.mchain.apollo.client.utils.ErrorUtils;
import com.mchain.apollo.core.entity.Message;
import com.mchain.test.common.ParamUtils;
import com.mchain.test.config.dao.ConfigChaincodeEntityRepository;
import com.mchain.test.config.entity.ConfigChaincode;
import com.mchain.test.config.entity.ConfigChaincodeEntity;
import com.mchain.test.entity.CommentLog;

@Component
public class CommentLogSDKClient {
//GENERATE_START	
	private final static String BUSINESS_CODE = "COMMENTLOG";
	
	@Autowired
	private SDKClientFactory sdkClientFactory;
	
	@Autowired
	private ConfigChaincodeEntityRepository chaincodeRepository;
	
	private ConfigChaincode getChaincode(String businessCode) throws Exception{
		Optional<ConfigChaincodeEntity> optResult = chaincodeRepository.findById(businessCode);
		if(optResult.isPresent()) return optResult.get().getChaincode();
		throw new Exception("Business entity's chaincode has not been set");
	}
	/**
	 * 新建业务对象
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Message<Object> save(CommentLog entity) throws Exception{
		String tranCode = "CreateCommentLog";
		Boolean asyncInvoke = false;
		
		if(entity.getUuid() == null || entity.getUuid().length() == 0) entity.setUuid(UUID.randomUUID().toString());
		if(entity.getDataType() == null || entity.getDataType().length() == 0) entity.setDataType("CommentLog");
		DirectSDKClient sdkClient = sdkClientFactory.create();
		ConfigChaincode cc = this.getChaincode(BUSINESS_CODE);
		return sdkClient.invokeLedger(cc.getChannelName(), cc.getChaincodeName(), tranCode, null, asyncInvoke, entity, null);
	}
	/**
	 * 修改业务对象
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Message<Object> update(CommentLog entity) throws Exception{
		String tranCode = "UpdateCommentLog";
		Boolean asyncInvoke = false;
		DirectSDKClient sdkClient = sdkClientFactory.create();
		ConfigChaincode cc = this.getChaincode(BUSINESS_CODE);
		return sdkClient.invokeLedger(cc.getChannelName(), cc.getChaincodeName(), tranCode, null, asyncInvoke, entity, null);
	}
	/**
	 * 根据ID删除业务对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Message<Object> delete(String id) throws Exception{
		String tranCode = "DelCommentLog";
		Boolean asyncInvoke = false;
		Map<String, String> conditions = new WeakHashMap<String, String>();
		conditions.put("id", id);
		DirectSDKClient sdkClient = sdkClientFactory.create();
		ConfigChaincode cc = this.getChaincode(BUSINESS_CODE);
		return sdkClient.invokeLedger(cc.getChannelName(), cc.getChaincodeName(), tranCode, null, asyncInvoke, null, conditions);
	}
	/**
	 * 根据主键获取业务对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Message<CommentLog> get(String id) throws Exception{
		String tranCode = "SelectCommentLog";
		CommentLog entity = new CommentLog();
		
		entity.setUuid(id);
		Boolean hasFile = false;
		TypeReference<CommentLog> itemType = new TypeReference<CommentLog>() {};
		DirectSDKClient sdkClient = sdkClientFactory.create();
		ConfigChaincode cc = this.getChaincode(BUSINESS_CODE);
		Message<List<CommentLog>> result = sdkClient.queryForDataList(cc.getChannelName(), cc.getChaincodeName(), tranCode, hasFile, null, entity, itemType);
		if(!result.isSuccess()) return ErrorUtils.toErrorMessage(result);
		if(result.getRetObject().size() == 0) {
			return new Message<CommentLog>("ERR_NOT_FOUND", "getCommentLog", "Entity not found");
		}
		return new Message<CommentLog>(result.getRetObject().get(0));
	}
	/**
	 * 查询所有业务对象
	 * @return
	 * @throws Exception
	 */
	public Message<List<CommentLog>> queryAll() throws Exception{
		String tranCode = "SelectAllCommentLog";
		Boolean hasFile = false;
		TypeReference<CommentLog> itemType = new TypeReference<CommentLog>() {};
		DirectSDKClient sdkClient = sdkClientFactory.create();
		ConfigChaincode cc = this.getChaincode(BUSINESS_CODE);
		Message<List<CommentLog>> result = sdkClient.queryForDataList(cc.getChannelName(), cc.getChaincodeName(), tranCode, hasFile, null, null, itemType);
		return result;
	}
	/**
	 * 根据条件查询返回业务对象列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Message<List<CommentLog>> query(Map<?,?> params) throws Exception{
		String tranCode = "QueryCommentLog";
		Boolean hasFile = false;
		// 条件设置
		LedgerQuerySpecification conditions = this.getConditions(params);
		TypeReference<CommentLog> itemType = new TypeReference<CommentLog>() {};
		DirectSDKClient sdkClient = sdkClientFactory.create();
		ConfigChaincode cc = this.getChaincode(BUSINESS_CODE);
		Message<List<CommentLog>> result = sdkClient.queryForDataList(cc.getChannelName(), cc.getChaincodeName(), tranCode, hasFile, conditions, null, itemType);
		return result;
	}
	/**
	 * 执行翻页查询
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Message<LedgerPagination<CommentLog>> paginate(Map<?,?> params) throws Exception{
		String tranCode = "PaginateCommentLog";
		Boolean hasFile = false;
		// Get bookmark
		String bookMark = ParamUtils.getString(params, "bookMark");
		Integer pageSize = ParamUtils.getInteger(params, "pageSize");
		// 条件设置
		LedgerQuerySpecification conditions = this.getConditions(params);
		ParameterizedTypeReference<Message<LedgerPagination<CommentLog>>> returnType = new  ParameterizedTypeReference<Message<LedgerPagination<CommentLog>>>() {};
		DirectSDKClient sdkClient = sdkClientFactory.create();
		ConfigChaincode cc = this.getChaincode(BUSINESS_CODE);
		return sdkClient.paginate(cc.getChannelName(), cc.getChaincodeName(), tranCode, hasFile, bookMark, pageSize, conditions, null, returnType);
	}
	
	/**
	 * 构造查询条件
	 * @param params
	 * @return
	 */
	private LedgerQuerySpecification getConditions(Map<?,?> params) {
		List<String> reservedList = Arrays.asList(new String[] {"bookMark","pageSize"});
		if(params == null) return null;
		LedgerQuerySpecification conditions = null;
		for(Object key : params.keySet()) {
			String fieldName = key.toString();
			if(reservedList.indexOf(fieldName) > -1) continue;
			if(conditions == null) conditions = new LedgerQuerySpecification();
			conditions.and(key.toString(), params.get(key).toString());
		}
		return conditions;
	}
//GENERATE_START
	
}

