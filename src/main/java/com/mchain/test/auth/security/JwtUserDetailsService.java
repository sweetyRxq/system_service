package com.mchain.test.auth.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mchain.apollo.core.hibernate.HQuerySpecificationBuilder;
import com.mchain.apollo.core.utils.MapBuilder;
import com.mchain.test.auth.dao.AuthUserRepository;
import com.mchain.test.auth.entity.AuthFunction;
import com.mchain.test.auth.entity.AuthRole;
import com.mchain.test.auth.entity.AuthUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AuthUserRepository authUserRepository;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Map<String, Object> params = new MapBuilder<String, Object>().append("account", username).append("email", username).build();
		// 开始根据条件查询用户
		Specification<AuthUser> cons = new HQuerySpecificationBuilder<AuthUser>(params)
				.append("account", "=", "account", false).append("email", "=", "email", true).build();
		Optional<AuthUser> optAuthUser = authUserRepository.findOne(cons);
		
		if(!optAuthUser.isPresent()){
			throw new UsernameNotFoundException(String.format("user [%s] not found.", username));
		}
		AuthUser user = optAuthUser.get();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(user.getRole() != null) {
				AuthRole authRole = user.getRole();
				authorities.add(new SimpleGrantedAuthority(authRole.getCode()));
				if(authRole.getFunctions() != null && authRole.getFunctions().size() > 0) {
					AuthFunction[] functions = new AuthFunction[authRole.getFunctions().size()];
					functions = authRole.getFunctions().toArray(functions);
					for(int j = 0; j < functions.length; j++) {
						authorities.add(new SimpleGrantedAuthority(functions[j].getCode()));
					}
				}
		}
		String fullName = null;
		JwtUserDetails userDetails = new JwtUserDetails(
				user.getEmail(),
				user.getAccount(),
				username,
				fullName,
				user.getPassword(),
				user.getId(),
				user.getActive(),
				user.getOrganization(),
				authorities
				);
		return userDetails;
	}

}

