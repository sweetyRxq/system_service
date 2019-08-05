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
import com.mchain.test.auth.dao.AuthFunctionRepository;
import com.mchain.test.auth.entity.AuthFunction;

@RestController
@RequestMapping(value = "/api/sys/function")
public class AuthFunctionService {
	
	private final static String API = "/api/sys/function";
	@Autowired
	private AuthFunctionRepository authFunctionRepository;
	
	/**
	 * 保存菜单
	 * @param entity 菜单数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<AuthFunction> save(@RequestBody AuthFunction entity){
		try {
			authFunctionRepository.save(entity);
			return new Message<AuthFunction>(entity);
		}catch(Exception e) {
			return new Message<AuthFunction>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 修改菜单
	 * @param entity 菜单数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<AuthFunction> update(@RequestBody AuthFunction entity){
		try {
			Optional<AuthFunction> optAuthFunction = authFunctionRepository.findById(entity.getCode());
			if(optAuthFunction.isPresent()) {
				authFunctionRepository.save(entity);
				return new Message<AuthFunction>(entity);
			}else {
				return new Message<AuthFunction>("ERR_NOT_FOUND", String.format("PUT %s", API), "Function not found.");
			}
		}catch(Exception e) {
			return new Message<AuthFunction>("500", String.format("PUT %s", API), e.getMessage());
		}
	}
	/**
	 * 根据编码删除菜单
	 * @param code 菜单编码
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public Message<Integer> delete(@PathVariable("code") String code){
		try {
			Optional<AuthFunction> optAuthFunction = authFunctionRepository.findById(code);
			if(optAuthFunction.isPresent()) {
				authFunctionRepository.deleteById(code);
				return new Message<Integer>(1);
			}else {
				return new Message<Integer>("ERR_NOT_FOUND", String.format("DELETE %s/%s", API, code), "Function not found.");
			}
		}catch(Exception e) {
			return new Message<Integer>("500", String.format("DELETE %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据编码获取菜单信息
	 * @param code 菜单编码
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public Message<AuthFunction> get(@PathVariable("code") String code){
		try {
			Optional<AuthFunction> optAuthFunction = authFunctionRepository.findById(code);
			if(optAuthFunction.isPresent()) {
				return new Message<AuthFunction>(optAuthFunction.get());
			}else {
				return new Message<AuthFunction>("ERR_NOT_FOUND", String.format("GET %s/%s", API, code), "Function not found.");
			}
		}catch(Exception e) {
			return new Message<AuthFunction>("500", String.format("GET %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据条件查询菜单并返回列表信息
	 * @param params 查询条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<AuthFunction>> query(@RequestParam Map<?,?> params){
		try {
			Specification<AuthFunction> spec = this.genSpecification(params);
			List<AuthFunction> list = authFunctionRepository.findAll(spec);
			return new Message<List<AuthFunction>>(list);
		}catch(Exception e) {
			return new Message<List<AuthFunction>>("500", String.format("GET %s", API), e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询菜单并返回翻页信息
	 * @param params 查询条件
	 * @param pageable 翻页信息
	 * @return
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<Pagination<AuthFunction>> paginate(@RequestParam Map<?,?> params, Pageable pageable){
		try {
			Specification<AuthFunction> spec = this.genSpecification(params);
			Page<AuthFunction> list = authFunctionRepository.findAll(spec, pageable);
			Pagination<AuthFunction> pagination = Pagination.from(list);
			return new Message<Pagination<AuthFunction>>(pagination);
		}catch(Exception e) {
			return new Message<Pagination<AuthFunction>>("500", String.format("GET %s/paginate", API), e.getMessage());
		}
	}
	/**
	 * 构造查询条件
	 * @param conditions 查询条件
	 * @return
	 */
	private Specification<AuthFunction> genSpecification(Map<?,?> conditions) {
		Specification<AuthFunction> spec = new HQuerySpecificationBuilder<AuthFunction>(conditions)
				.append("code", "=", "code", false)
				.append("name", "=", "name", false).build();
		return spec;
	}
}

