package com.mchain.test.auth.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mchain.test.auth.entity.AuthOrganization;

public class JwtUserDetails implements UserDetails {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3070348040862256675L;
	private String email;
	private String account;
	private String username;
	private String fullName;
	private String password;
	private Integer active;
	private String userId;
	private AuthOrganization organization;
	private List<GrantedAuthority> authorities;
	private Date lastPasswordResetDate;
	
	public JwtUserDetails(String email, String account, String username, String fullName, String password, String userId, Integer active, 
			AuthOrganization organization,
			List<GrantedAuthority> authorities){
		this.email = email;
		this.account = account;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.userId = userId;
		this.active = active;
		this.organization = organization;
		this.authorities = authorities;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getFullName(){
		return this.fullName;
	}

	public String getUsername() {
		if(this.username != null) return this.username;
		if(this.email != null) return this.email;
		return this.account;
	}
	
	public String getAccount(){
		return this.account;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		if(this.active != null && this.active.intValue() == 1) return true;
		return false;
	}
	
	@JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public AuthOrganization getOrganization() {
		return this.organization;
	}

}

