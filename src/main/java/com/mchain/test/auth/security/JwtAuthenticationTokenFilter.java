package com.mchain.test.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 授权令牌处理过滤器
 * @author longjinsheng
 *
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Value("${jwt.header}")
    private String tokenHeader;
	
	@Value("${jwt.tokenHead}")
    private String tokenHead;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            // Get the user name from Token
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            //logger.info("checking authentication " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                try {
	            	UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	
	                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
	                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	                            userDetails, null, userDetails.getAuthorities());
	                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
	                            request));
	                    //logger.info("authenticated user " + username + ", setting security context");
	                    SecurityContextHolder.getContext().setAuthentication(authentication);
	                    //logger.info("finish to set the security context");
	                }
                }catch(Exception e) {
                	System.out.println(String.format("Error accured while request for %s from %s", 
                			request.getRequestURI(), 
                			request.getRemoteAddr() == null ? request.getRemoteHost() : request.getRemoteAddr()));
                }
            }
        }

        chain.doFilter(request, response);
	}

}

