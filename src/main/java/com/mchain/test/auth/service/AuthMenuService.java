package com.mchain.test.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mchain.apollo.core.entity.Message;
import com.mchain.apollo.core.entity.Pagination;
import com.mchain.apollo.core.hibernate.HQuerySpecificationBuilder;
import com.mchain.test.auth.Authorities;
import com.mchain.test.auth.dao.AuthMenuRepository;
import com.mchain.test.auth.entity.AuthMenu;

@RestController
@RequestMapping(value = "/api/sys/menu")
public class AuthMenuService {

	private final static String API = "/api/sys/menu";
	@Autowired
	private AuthMenuRepository authMenuRepository;
	
	/**
	 * 保存菜单
	 * @param entity 菜单数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message<AuthMenu> save(@RequestBody AuthMenu entity){
		try {
			authMenuRepository.save(entity);
			return new Message<AuthMenu>(entity);
		}catch(Exception e) {
			return new Message<AuthMenu>("500", String.format("POST %s", API), e.getMessage());
		}
	}
	/**
	 * 修改菜单
	 * @param entity 菜单数据
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Message<AuthMenu> update(@RequestBody AuthMenu entity){
		try {
			Optional<AuthMenu> optAuthMenu = authMenuRepository.findById(entity.getCode());
			if(optAuthMenu.isPresent()) {
				authMenuRepository.save(entity);
				return new Message<AuthMenu>(entity);
			}else {
				return new Message<AuthMenu>("ERR_NOT_FOUND", String.format("PUT %s", API), "Menu not found.");
			}
		}catch(Exception e) {
			return new Message<AuthMenu>("500", String.format("PUT %s", API), e.getMessage());
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
			Optional<AuthMenu> optAuthMenu = authMenuRepository.findById(code);
			if(optAuthMenu.isPresent()) {
				authMenuRepository.deleteById(code);
				return new Message<Integer>(1);
			}else {
				return new Message<Integer>("ERR_NOT_FOUND", String.format("DELETE %s/%s", API, code), "Menu not found.");
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
	public Message<AuthMenu> get(@PathVariable("code") String code){
		try {
			Optional<AuthMenu> optAuthMenu = authMenuRepository.findById(code);
			if(optAuthMenu.isPresent()) {
				return new Message<AuthMenu>(optAuthMenu.get());
			}else {
				return new Message<AuthMenu>("ERR_NOT_FOUND", String.format("GET %s/%s", API, code), "Menu not found.");
			}
		}catch(Exception e) {
			return new Message<AuthMenu>("500", String.format("GET %s/%s", API, code), e.getMessage());
		}
	}
	/**
	 * 根据条件查询菜单并返回列表信息
	 * @param params 查询条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message<List<AuthMenu>> query(@RequestParam Map<?,?> params){
		try {
			Specification<AuthMenu> spec = this.genSpecification(params);
			List<AuthMenu> list = authMenuRepository.findAll(spec);
			return new Message<List<AuthMenu>>(list);
		}catch(Exception e) {
			return new Message<List<AuthMenu>>("500", String.format("GET %s", API), e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询菜单并返回翻页信息
	 * @param params 查询条件
	 * @param pageable 翻页信息
	 * @return
	 */
	@RequestMapping(value = "/paginate", method = RequestMethod.GET)
	public Message<Pagination<AuthMenu>> paginate(@RequestParam Map<?,?> params, Pageable pageable){
		try {
			Specification<AuthMenu> spec = this.genSpecification(params);
			Page<AuthMenu> list = authMenuRepository.findAll(spec, pageable);
			Pagination<AuthMenu> pagination = Pagination.from(list);
			return new Message<Pagination<AuthMenu>>(pagination);
		}catch(Exception e) {
			return new Message<Pagination<AuthMenu>>("500", String.format("GET %s/paginate", API), e.getMessage());
		}
	}
	/**
	 * 构造查询条件
	 * @param conditions 查询条件
	 * @return
	 */
	private Specification<AuthMenu> genSpecification(Map<?,?> conditions) {
		Specification<AuthMenu> spec = new HQuerySpecificationBuilder<AuthMenu>(conditions)
				.append("code", "=", "code", false)
				.append("name", "=", "name", false).build();
		return spec;
	}
	
	@RequestMapping(value = "/authed",method = RequestMethod.GET)
	public Message<List<AuthMenu>> fetchAuthMenus(){
		List<AuthMenu> menus = authMenuRepository.fetchTopMenus();
		List<AuthMenu> list = filterMenus(menus);
		return new Message<List<AuthMenu>>(list);
	}
	
	private List<AuthMenu> filterMenus(List<AuthMenu> source){
		List<String> authorities = this.getAuthorities();
		if(hasSuperAdmin(authorities)) {
			return source;
		}else {
			List<AuthMenu> newList = new ArrayList<AuthMenu>();
			for(AuthMenu sysMenu : source) {
				if(!authorities.contains(sysMenu.getFunction().getCode())) continue;
				if(sysMenu.getChildren() != null) {
					List<AuthMenu> children = new ArrayList<AuthMenu>();
					for(AuthMenu subMenu : sysMenu.getChildren()) {
						if(!authorities.contains(subMenu.getFunction().getCode())) continue;
						children.add(subMenu);
					}
					sysMenu.setChildren(children);
				}
				newList.add(sysMenu);
			}
			return newList;
		}
	}
	
	private List<String> getAuthorities(){
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> result = new ArrayList<String>();
		for(GrantedAuthority item : authorities) {
			result.add(item.getAuthority());
		}
		return result;
	}
	
	private boolean hasSuperAdmin(List<String> items) {
		return items.contains(Authorities.RoleSuperAdmin);
	}

}

