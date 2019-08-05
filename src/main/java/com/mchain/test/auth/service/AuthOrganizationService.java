package com.mchain.test.auth.service;

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
import com.mchain.test.auth.dao.AuthOrganizationRepository;
import com.mchain.test.auth.entity.AuthOrganization;

@RestController
@RequestMapping(value = "/api/sys/organization")
public class AuthOrganizationService {
	
	private final static String API = "/api/sys/organization";
	@Autowired
	private AuthOrganizationRepository authOrganizationRepository;
	
	/**
	 * 保存部门
	 * @param entity 部门数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<AuthOrganization> save(@RequestBody AuthOrganization entity){
		try {
			authOrganizationRepository.save(entity);
			return new Message<AuthOrganization>(entity);
		}catch(Exception e) {
			return new Message<AuthOrganization>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 修改部门
	 * @param entity 部门数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<AuthOrganization> update(@RequestBody AuthOrganization entity){
		try {
			Optional<AuthOrganization> optAuthOrganization = authOrganizationRepository.findById(entity.getCode());
			if(optAuthOrganization.isPresent()) {
				authOrganizationRepository.save(entity);
				return new Message<AuthOrganization>(entity);
			}else {
				return new Message<AuthOrganization>("ERR_NOT_FOUND", String.format("PUT %s", API), "Organization not found.");
			}
		}catch(Exception e) {
			return new Message<AuthOrganization>("500", String.format("PUT %s", API), e.getMessage());
		}
	}
	/**
	 * 根据编码删除部门
	 * @param code 部门编码
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public Message<Integer> delete(@PathVariable("code") String code){
		try {
			Optional<AuthOrganization> optAuthOrganization = authOrganizationRepository.findById(code);
			if(optAuthOrganization.isPresent()) {
				authOrganizationRepository.deleteById(code);
				return new Message<Integer>(1);
			}else {
				return new Message<Integer>("ERR_NOT_FOUND", String.format("DELETE %s/%s", API, code), "Organization not found.");
			}
		}catch(Exception e) {
			return new Message<Integer>("500", String.format("DELETE %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据编码获取部门信息
	 * @param code 部门编码
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public Message<AuthOrganization> get(@PathVariable("code") String code){
		try {
			Optional<AuthOrganization> optAuthOrganization = authOrganizationRepository.findById(code);
			if(optAuthOrganization.isPresent()) {
				return new Message<AuthOrganization>(optAuthOrganization.get());
			}else {
				return new Message<AuthOrganization>("ERR_NOT_FOUND", String.format("GET %s/%s", API, code), "Organization not found.");
			}
		}catch(Exception e) {
			return new Message<AuthOrganization>("500", String.format("GET %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据条件查询部门并返回列表信息
	 * @param params 查询条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<AuthOrganization>> query(@RequestParam Map<?,?> params){
		try {
			Specification<AuthOrganization> spec = this.genSpecification(params);
			List<AuthOrganization> list = authOrganizationRepository.findAll(spec);
			return new Message<List<AuthOrganization>>(list);
		}catch(Exception e) {
			return new Message<List<AuthOrganization>>("500", String.format("GET %s", API), e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询部门并返回翻页信息
	 * @param params 查询条件
	 * @param pageable 翻页信息
	 * @return
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<Pagination<AuthOrganization>> paginate(@RequestParam Map<?,?> params, Pageable pageable){
		try {
			Specification<AuthOrganization> spec = this.genSpecification(params);
			Page<AuthOrganization> list = authOrganizationRepository.findAll(spec, pageable);
			Pagination<AuthOrganization> pagination = Pagination.from(list);
			return new Message<Pagination<AuthOrganization>>(pagination);
		}catch(Exception e) {
			return new Message<Pagination<AuthOrganization>>("500", String.format("GET %s/paginate", API), e.getMessage());
		}
	}
	/**
	 * 构造查询条件
	 * @param conditions 查询条件
	 * @return
	 */
	private Specification<AuthOrganization> genSpecification(Map<?,?> conditions) {
		Specification<AuthOrganization> spec = new HQuerySpecificationBuilder<AuthOrganization>(conditions)
				.append("code", "=", "code", false)
				.append("name", "=", "name", false).build();
		return spec;
	}
}

