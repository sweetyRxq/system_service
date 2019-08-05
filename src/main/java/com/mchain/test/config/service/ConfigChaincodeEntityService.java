package com.mchain.test.config.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mchain.apollo.core.entity.Message;
import com.mchain.apollo.core.entity.Pagination;
import com.mchain.apollo.core.hibernate.HQuerySpecificationBuilder;
import com.mchain.test.config.dao.ConfigChaincodeEntityRepository;
import com.mchain.test.config.entity.ConfigChaincodeEntity;

@RestController
@RequestMapping(value = "/api/config/chaincode-entity")
public class ConfigChaincodeEntityService {

	private final static String API = "/api/config/chaincode-entity";
	@Autowired
	private ConfigChaincodeEntityRepository configChaincodeEntityRepository;
	
	/**
	 * 保存智能合约
	 * @param entity 智能合约数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<ConfigChaincodeEntity> save(@RequestBody ConfigChaincodeEntity entity){
		try {
			configChaincodeEntityRepository.save(entity);
			return new Message<ConfigChaincodeEntity>(entity);
		}catch(Exception e) {
			return new Message<ConfigChaincodeEntity>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 修改智能合约
	 * @param entity 智能合约数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<ConfigChaincodeEntity> update(@RequestBody ConfigChaincodeEntity entity){
		try {
			Optional<ConfigChaincodeEntity> optConfigChaincodeEntity = configChaincodeEntityRepository.findById(entity.getName());
			if(optConfigChaincodeEntity.isPresent()) {
				configChaincodeEntityRepository.save(entity);
				return new Message<ConfigChaincodeEntity>(entity);
			}else {
				return new Message<ConfigChaincodeEntity>("ERR_NOT_FOUND", String.format("PUT %s", API), "Chaincode not found.");
			}
		}catch(Exception e) {
			return new Message<ConfigChaincodeEntity>("500", String.format("PUT %s", API), e.getMessage());
		}
	}
	/**
	 * 根据编码删除智能合约
	 * @param code 智能合约编码
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Message<Integer> delete(@PathVariable("id") String id){
		try {
			Optional<ConfigChaincodeEntity> optConfigChaincodeEntity = configChaincodeEntityRepository.findById(id);
			if(optConfigChaincodeEntity.isPresent()) {
				configChaincodeEntityRepository.deleteById(id);
				return new Message<Integer>(1);
			}else {
				return new Message<Integer>("ERR_NOT_FOUND", String.format("DELETE %s/%s", API, id), "Chaincode not found.");
			}
		}catch(Exception e) {
			return new Message<Integer>("500", String.format("DELETE %s/%s", API, id), e.getMessage());
		}
	}
	/**
	 * 根据编码获取智能合约信息
	 * @param code 智能合约编码
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Message<ConfigChaincodeEntity> get(@PathVariable("id") String id){
		try {
			Optional<ConfigChaincodeEntity> optConfigChaincodeEntity = configChaincodeEntityRepository.findById(id);
			if(optConfigChaincodeEntity.isPresent()) {
				return new Message<ConfigChaincodeEntity>(optConfigChaincodeEntity.get());
			}else {
				return new Message<ConfigChaincodeEntity>("ERR_NOT_FOUND", String.format("GET %s/%s", API, id), "Chaincode not found.");
			}
		}catch(Exception e) {
			return new Message<ConfigChaincodeEntity>("500", String.format("GET %s/%s", API, id), e.getMessage());
		}
	}
	/**
	 * 根据条件查询智能合约并返回列表信息
	 * @param params 查询条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<ConfigChaincodeEntity>> query(@RequestParam Map<?,?> params){
		try {
			Specification<ConfigChaincodeEntity> spec = this.genSpecification(params);
			List<ConfigChaincodeEntity> list = configChaincodeEntityRepository.findAll(spec);
			return new Message<List<ConfigChaincodeEntity>>(list);
		}catch(Exception e) {
			return new Message<List<ConfigChaincodeEntity>>("500", String.format("GET %s", API), e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询智能合约并返回翻页信息
	 * @param params 查询条件
	 * @param pageable 翻页信息
	 * @return
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<Pagination<ConfigChaincodeEntity>> paginate(@RequestParam Map<?,?> params, Pageable pageable){
		try {
			Specification<ConfigChaincodeEntity> spec = this.genSpecification(params);
			Page<ConfigChaincodeEntity> list = configChaincodeEntityRepository.findAll(spec, pageable);
			Pagination<ConfigChaincodeEntity> pagination = Pagination.from(list);
			return new Message<Pagination<ConfigChaincodeEntity>>(pagination);
		}catch(Exception e) {
			return new Message<Pagination<ConfigChaincodeEntity>>("500", String.format("GET %s/paginate", API), e.getMessage());
		}
	}
	/**
	 * 构造查询条件
	 * @param conditions 查询条件
	 * @return
	 */
	private Specification<ConfigChaincodeEntity> genSpecification(Map<?,?> conditions) {
		Specification<ConfigChaincodeEntity> spec = new HQuerySpecificationBuilder<ConfigChaincodeEntity>(conditions)
				.append("id", "=", "id", false)
				.append("channelName", "=", "channelName", false)
				.append("chaincodeName", "=", "chaincodeName", false).build();
		return spec;
	}
}

