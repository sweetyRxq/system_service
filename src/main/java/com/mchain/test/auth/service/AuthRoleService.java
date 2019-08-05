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
import com.mchain.test.auth.dao.AuthRoleRepository;
import com.mchain.test.auth.entity.AuthRole;

@RestController
@RequestMapping(value = "/api/sys/role")
public class AuthRoleService {

	private final static String API = "/api/sys/role";
	@Autowired
	private AuthRoleRepository authRoleRepository;
	
	/**
	 * 保存角色
	 * @param entity 角色数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<AuthRole> save(@RequestBody AuthRole entity){
		try {
			authRoleRepository.save(entity);
			return new Message<AuthRole>(entity);
		}catch(Exception e) {
			return new Message<AuthRole>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 修改角色
	 * @param entity 角色数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<AuthRole> update(@RequestBody AuthRole entity){
		try {
			Optional<AuthRole> optAuthRole = authRoleRepository.findById(entity.getCode());
			if(optAuthRole.isPresent()) {
				authRoleRepository.save(entity);
				return new Message<AuthRole>(entity);
			}else {
				return new Message<AuthRole>("ERR_NOT_FOUND", String.format("PUT %s", API), "Role not found.");
			}
		}catch(Exception e) {
			return new Message<AuthRole>("500", String.format("PUT %s", API), e.getMessage());
		}
	}
	/**
	 * 根据编码删除角色
	 * @param code 角色编码
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public Message<Integer> delete(@PathVariable("code") String code){
		try {
			Optional<AuthRole> optAuthRole = authRoleRepository.findById(code);
			if(optAuthRole.isPresent()) {
				authRoleRepository.deleteById(code);
				return new Message<Integer>(1);
			}else {
				return new Message<Integer>("ERR_NOT_FOUND", String.format("DELETE %s/%s", API, code), "Role not found.");
			}
		}catch(Exception e) {
			return new Message<Integer>("500", String.format("DELETE %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据编码获取角色信息
	 * @param code 角色编码
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public Message<AuthRole> get(@PathVariable("code") String code){
		try {
			Optional<AuthRole> optAuthRole = authRoleRepository.findById(code);
			if(optAuthRole.isPresent()) {
				return new Message<AuthRole>(optAuthRole.get());
			}else {
				return new Message<AuthRole>("ERR_NOT_FOUND", String.format("GET %s/%s", API, code), "Role not found.");
			}
		}catch(Exception e) {
			return new Message<AuthRole>("500", String.format("GET %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据条件查询角色并返回列表信息
	 * @param params 查询条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<AuthRole>> query(@RequestParam Map<?,?> params){
		try {
			Specification<AuthRole> spec = this.genSpecification(params);
			List<AuthRole> list = authRoleRepository.findAll(spec);
			return new Message<List<AuthRole>>(list);
		}catch(Exception e) {
			return new Message<List<AuthRole>>("500", String.format("GET %s", API), e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询角色并返回翻页信息
	 * @param params 查询条件
	 * @param pageable 翻页信息
	 * @return
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<Pagination<AuthRole>> paginate(@RequestParam Map<?,?> params, Pageable pageable){
		try {
			Specification<AuthRole> spec = this.genSpecification(params);
			Page<AuthRole> list = authRoleRepository.findAll(spec, pageable);
			Pagination<AuthRole> pagination = Pagination.from(list);
			return new Message<Pagination<AuthRole>>(pagination);
		}catch(Exception e) {
			return new Message<Pagination<AuthRole>>("500", String.format("GET %s/paginate", API), e.getMessage());
		}
	}
	/**
	 * 构造查询条件
	 * @param conditions 查询条件
	 * @return
	 */
	private Specification<AuthRole> genSpecification(Map<?,?> conditions) {
		Specification<AuthRole> spec = new HQuerySpecificationBuilder<AuthRole>(conditions)
				.append("code", "=", "code", false)
				.append("name", "=", "name", false).build();
		return spec;
	}
}

