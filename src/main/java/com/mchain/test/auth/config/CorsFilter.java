/**
 * Copyright @2018 Ming-Chain (ShenZhen) Technology Co., Ltd. All rights reserved.
 *
 * @Title: CrosFilter.java
 * @Project: apollo
 * @Package: com.mchain.apollo.config
 * @Description: Filter enable Access-Control-Allow-Origin
 * @author: longjinsheng  
 * @date: 2018年12月27日 下午5:00:46
 * @version: V1.0  
 **/
package com.mchain.test.auth.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CrosFilter
 * @Description: TODO
 * @author: longjinsheng
 * @date: 2018年12月27日 下午5:00:46
 */
public class CorsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Do nothing here
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse hsResponse = (HttpServletResponse)response;
		HttpServletRequest hsRequest = (HttpServletRequest)request;
		
		String origin = hsRequest.getHeader("Origin");
		if(origin == null) {
			origin = hsRequest.getHeader("Referer");
		}
		if(origin == null) origin = "*";
		
		hsResponse.setHeader("Access-Control-Allow-Origin", origin);
		hsResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		hsResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Access-Token, Authorization, X-Token");
		hsResponse.setHeader("Access-Control-Allow-Credentials", "true");
		hsResponse.setHeader("Access-Control-Max-Age", "180");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// Do nothing here.
	}

}

