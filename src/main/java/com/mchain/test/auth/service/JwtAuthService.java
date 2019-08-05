package com.mchain.test.auth.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mchain.apollo.core.entity.Message;
import com.mchain.test.auth.entity.AuthUser;
import com.mchain.test.auth.security.IAuthService;

@RestController
public class JwtAuthService {

	@Value("${jwt.header}")
    private String tokenHeader;
	
	@Autowired
    private IAuthService authService;
	
	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public Message<Map<String, Object>> createAuthenticationToken(@RequestBody HashMap<String, String> request, HttpServletRequest httpRequest, HttpSession session) throws AuthenticationException{
        String userName = request.get("username");
        String password = request.get("password");
        Message<Map<String, Object>> result = authService.login(userName, password);
        // Return the token
        return result;
    }
	
	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public Message<String> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return new Message<String>("ERR01", "refreshAndGetAuthenticationToken", "Token is not expired");
        } else {
            return new Message<String>(refreshedToken);
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public AuthUser register(@RequestBody AuthUser addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }

}

