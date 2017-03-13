package com.nttdata.tdb.web.core.auth;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider { //
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		
		 return super.authenticate(authentication);		
	 }

}