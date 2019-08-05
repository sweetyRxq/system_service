package com.mchain.test.auth.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mchain.apollo.core.entity.Message;
import com.mchain.apollo.core.entity.Pagination;
import com.mchain.apollo.core.hibernate.HQuerySpecificationBuilder;
import com.mchain.test.auth.dao.AuthUserRepository;
import com.mchain.test.auth.entity.AuthUser;

@RestController
@RequestMapping(value = "/api/sys/user")
public class AuthUserService {
	
	private final static String API = "/api/sys/user";
	@Autowired
	private AuthUserRepository authUserRepository;
	
	/**
	 * 保存用户
	 * @param entity 用户数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<AuthUser> save(@RequestBody AuthUser entity){
		try {
			authUserRepository.save(entity);
			return new Message<AuthUser>(entity);
		}catch(Exception e) {
			return new Message<AuthUser>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 修改用户
	 * @param entity 用户数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<AuthUser> update(@RequestBody AuthUser entity){
		try {
			Optional<AuthUser> optAuthUser = authUserRepository.findById(entity.getId());
			if(optAuthUser.isPresent()) {
				authUserRepository.save(entity);
				return new Message<AuthUser>(entity);
			}else {
				return new Message<AuthUser>("ERR_NOT_FOUND", String.format("PUT %s", API), "User not found.");
			}
		}catch(Exception e) {
			return new Message<AuthUser>("500", String.format("PUT %s", API), e.getMessage());
		}
	}
	/**
	 * 根据ID删除用户
	 * @param code 用户编码
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Message<Integer> delete(@PathVariable("id") String code){
		try {
			Optional<AuthUser> optAuthUser = authUserRepository.findById(code);
			if(optAuthUser.isPresent()) {
				authUserRepository.deleteById(code);
				return new Message<Integer>(1);
			}else {
				return new Message<Integer>("ERR_NOT_FOUND", String.format("DELETE %s/%s", API, code), "User not found.");
			}
		}catch(Exception e) {
			return new Message<Integer>("500", String.format("DELETE %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据ID获取用户信息
	 * @param code 用户编码
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Message<AuthUser> get(@PathVariable("id") String code){
		try {
			Optional<AuthUser> optAuthUser = authUserRepository.findById(code);
			if(optAuthUser.isPresent()) {
				return new Message<AuthUser>(optAuthUser.get());
			}else {
				return new Message<AuthUser>("ERR_NOT_FOUND", String.format("GET %s/%s", API, code), "User not found.");
			}
		}catch(Exception e) {
			return new Message<AuthUser>("500", String.format("GET %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据条件查询用户并返回列表信息
	 * @param params 查询条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<AuthUser>> query(@RequestParam Map<?,?> params){
		try {
			Specification<AuthUser> spec = this.genSpecification(params);
			List<AuthUser> list = authUserRepository.findAll(spec);
			return new Message<List<AuthUser>>(list);
		}catch(Exception e) {
			return new Message<List<AuthUser>>("500", String.format("GET %s", API), e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询用户并返回翻页信息
	 * @param params 查询条件
	 * @param pageable 翻页信息
	 * @return
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<Pagination<AuthUser>> paginate(@RequestParam Map<?,?> params, Pageable pageable){
		try {
			Specification<AuthUser> spec = this.genSpecification(params);
			Page<AuthUser> list = authUserRepository.findAll(spec, pageable);
			Pagination<AuthUser> pagination = Pagination.from(list);
			return new Message<Pagination<AuthUser>>(pagination);
		}catch(Exception e) {
			return new Message<Pagination<AuthUser>>("500", String.format("GET %s/paginate", API), e.getMessage());
		}
	}
	
	/**
	 * 管理员重置用户密码
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public Message<Object> resetPassword(@RequestBody Map<String, String> params){
		try {
			String id = params.get("id");
			String password = params.get("password");
			String confirmPassword = params.get("confirmPassword");
			if(id == null || password == null || confirmPassword == null) {
				return new Message<Object>("ERR_PARAM_NOT_SUFFICENT", "", "Parameters are not sufficient");
			}
			if(!password.equals(confirmPassword)) {
				return new Message<Object>("ERR_PWD_NOT_SAME", "", "Confirm password should be the same as password");
			}
			Optional<AuthUser> optAuthUser = authUserRepository.findById(id);
			if(!optAuthUser.isPresent()) {
				return new Message<Object>("ERR_NOT_FOUND", String.format("GET %s/reset-password", API), "User not found.");
			}
			AuthUser user = optAuthUser.get();
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
			authUserRepository.save(user);
			return new Message<Object>("OK");
		}catch(Exception e) {
			return new Message<Object>("500", String.format("GET %s/reset-password", API), e.getMessage());
		}
	}
	
	/**
	 * 用户修改自己的密码
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public Message<Object> changePassword(@RequestBody Map<String, String> params){
		try {
			String id = params.get("id");
			String oldPassword = params.get("oldPassword");
			String password = params.get("password");
			String confirmPassword = params.get("confirmPassword");
			if(id == null || oldPassword == null || password == null || confirmPassword == null) {
				return new Message<Object>("ERR_PARAM_NOT_SUFFICENT", "", "Parameters are not sufficient");
			}
			if(!password.equals(confirmPassword)) {
				return new Message<Object>("ERR_PWD_NOT_SAME", "", "Confirm password should be the same as password");
			}
			Optional<AuthUser> optAuthUser = authUserRepository.findById(id);
			if(!optAuthUser.isPresent()) {
				return new Message<Object>("ERR_NOT_FOUND", String.format("GET %s/change-password", API), "User not found.");
			}
			AuthUser user = optAuthUser.get();
			if(!BCrypt.checkpw(oldPassword, user.getPassword())) {
				return new Message<Object>("ERR_OLD_PWD_NOT_MATCH", String.format("GET %s/change-password", API), "Old password not matched.");
			}
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
			authUserRepository.save(user);
			return new Message<Object>("OK");
		}catch(Exception e) {
			return new Message<Object>("500", String.format("GET %s/change-password", API), e.getMessage());
		}
	}
	
	/**
	 * 构造查询条件
	 * @param conditions 查询条件
	 * @return
	 */
	private Specification<AuthUser> genSpecification(Map<?,?> conditions) {
		Specification<AuthUser> spec = new HQuerySpecificationBuilder<AuthUser>(conditions)
				.append("code", "=", "code", false)
				.append("name", "=", "name", false).build();
		return spec;
	}
	
	
}

