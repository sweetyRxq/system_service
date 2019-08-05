package com.mchain.test.auth.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * 统一错误处理
 * @author longjinsheng
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;
    
    @Autowired
	MessageSource messageSource;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    	response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;  
        try {
        	//String locale = this.getLocale(request);
        	String errMsg = "Unauthorized";//messageSource.getMessage("err_msg_401", new Object[] {}, LocaleContextHolder.getLocale());
        	if(authException instanceof org.springframework.security.authentication.BadCredentialsException) {
        		errMsg = "User name or password not correct";//i18nHelper.get("err_bad_credentials", locale);
        	}else if(authException instanceof org.springframework.security.authentication.DisabledException) {
        		errMsg = "User is not actived";//i18nHelper.get("err_user_disabled", locale);
        	}
            out = response.getWriter();
            String url = request.getRequestURI() == null ? "unknown" : request.getRequestURI().toString();
            out.append(String.format("{\"retCode\":\"401\", \"errors\": [{\"code\":\"401\", \"function\":\"" + url + "\", \"statement\":\"%s\"}]}", errMsg));  
 
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (out != null) {  
                out.close();  
            }  
        }
    }
    
    /*private String getLocale(HttpServletRequest request) {
    	String name = "locale";
    	String locale = request.getParameter(name);
    	if(locale == null) locale = (String)request.getAttribute(name);
    	if(locale == null) locale = request.getHeader(name);
    	return locale;
    }*/
}

