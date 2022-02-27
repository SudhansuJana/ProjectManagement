package com.anupam.ProjectManagement.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.anupam.ProjectManagement.exceptions.InvalidLoginResponse;
import com.google.gson.Gson;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			AuthenticationException authException) throws IOException, ServletException {
		
		InvalidLoginResponse loginResponse = new InvalidLoginResponse();
		
		String jsonLoginResponse = new Gson().toJson(loginResponse);
		
		servletResponse.setContentType("application/json");
		servletResponse.setStatus(401);
		servletResponse.getWriter().print(jsonLoginResponse);
	}
}
