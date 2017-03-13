package com.nttdata.tdb.web.core.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.nttdata.tdb.constants.Constants;
import com.nttdata.tdb.services.user.UserService;

public class CustomAuthenticationFailureHandler extends	SimpleUrlAuthenticationFailureHandler {

	@Autowired
	UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		if (exception instanceof BadCredentialsException) {
			 super.setDefaultFailureUrl(Constants.LOGIN_ERROR_PASSWORD);
		} else if(exception instanceof LockedException) {
			super.setDefaultFailureUrl(Constants.LOGIN_ERROR_LOCKED);
		} else {
			super.setDefaultFailureUrl(Constants.LOGIN_ERROR_PASSWORD);
		}
		super.onAuthenticationFailure(request, response, exception);
	}
}
