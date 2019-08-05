package com.mchain.test.auth.security;

import java.util.Map;

import com.mchain.apollo.core.entity.Message;
import com.mchain.test.auth.entity.AuthUser;
/**
 * 自定义权限用户接口
 * @author longjinsheng
 *
 */
public interface IAuthService {
	
	public AuthUser register(AuthUser user);
	
	public Message<Map<String, Object>> login(String userName, String password);
	
	public String refresh(String oldToken);
}

