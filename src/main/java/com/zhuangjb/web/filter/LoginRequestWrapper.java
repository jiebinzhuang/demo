package com.zhuangjb.web.filter;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LoginRequestWrapper extends HttpServletRequestWrapper {
	private final PrincipalImpl principal;

	public LoginRequestWrapper(HttpServletRequest servletRequest, PrincipalImpl principal) {
		super(servletRequest);
		this.principal = principal;
	}

	@Override
	public String getRemoteUser() {
		return principal != null ? this.principal.getName() : null;
	}

	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}

	@Override
	public boolean isUserInRole(String role) {
		throw new RuntimeException("此方法尚未实现!");
	}
}
