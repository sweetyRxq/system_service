/**
 * Copyright @2018 Ming-Chain (ShenZhen) Technology Co., Ltd. All rights reserved.
 *
 * @Title: ApolloSecurityConfiguration.java
 * @Project: apollo
 * @Package: com.mchain.apollo.config
 * @Description: TODO
 * @author: longjinsheng  
 * @date: 2018年12月27日 下午5:08:58
 * @version: V1.0  
 **/
package com.mchain.test.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import com.mchain.test.auth.security.JwtAuthenticationEntryPoint;
import com.mchain.test.auth.security.JwtAuthenticationTokenFilter;


/**
 * @ClassName: AppSecurityConfiguration
 * @Description: TODO
 * @author: longjinsheng
 * @date: 2018年12月27日 下午5:08:58
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    public void configureAuthentication(AuthenticationManagerBuilder builder) throws Exception {
        builder
        	// Use the custom UserDetailsService
        	.userDetailsService(this.userDetailsService)
        	// Use BCrypt to hash the password
        	.passwordEncoder(passwordEncoder());
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
	
	@Bean
	CorsFilter corsFilter() {
		return new CorsFilter();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(corsFilter(),  SessionManagementFilter.class)
		.csrf().disable()
    	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
    	// 基于token，所以不需要session
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    	.authorizeRequests()
    	//.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
    	// 允许对于网站静态资源的无授权访问
    	.antMatchers(
    			HttpMethod.GET,
    			"/",
    			"/test",
    			"/*.html",
    			"/favicon.ico",
    			"/**/*.html",
    			"/**/*.css",
    			"/**/*.js",
    			"/**/*.jpg",
    			"/**/*.png",
    			"/**/*.gif",
    			"/**/*.apk"
    	).permitAll()
    	// 对于获取token的rest api要允许匿名访问
    	.antMatchers("/auth/**").permitAll()
    	//.antMatchers("/api/**").permitAll()
    	// 除上面外的所有请求全部需要鉴权认证
    	.anyRequest().authenticated();
		
		http
        .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
		http.headers().cacheControl();

	}
	
}

