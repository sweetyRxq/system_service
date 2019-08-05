package com.mchain.test.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mchain.apollo.client.sdk.DirectSDKClient;
import com.mchain.test.auth.entity.AuthOrganization;
import com.mchain.test.auth.security.JwtUserDetails;

@Component
public class SDKClientFactory {
	
	public DirectSDKClient create(String url, String orgName, String appId, String appSecret) {
		DirectSDKClient sdkClient = new DirectSDKClient(url, orgName, appId, appSecret);
		return sdkClient;
	}
	
	public DirectSDKClient create() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object details = auth.getPrincipal();
		JwtUserDetails userDetails = (JwtUserDetails)details;
		if(userDetails.getOrganization() == null) {
			throw new Exception("Organization not found");
		}
		AuthOrganization organization = userDetails.getOrganization();
		if(organization.getNetworkOrgName() == null || organization.getSdkUrl() == null ) {
			throw new Exception("Organization settings are not completed");
		}
		DirectSDKClient sdkClient = new DirectSDKClient(organization.getSdkUrl(), 
				organization.getNetworkOrgName(), 
				organization.getSdkAppId(), organization.getSdkAppSecret());
		return sdkClient;
	}
	
	
}

