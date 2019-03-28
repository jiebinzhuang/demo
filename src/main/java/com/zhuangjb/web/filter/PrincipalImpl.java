package com.zhuangjb.web.filter;

import java.security.Principal;
import java.util.Map;

public class PrincipalImpl implements Principal {
	private final String name;
	private Map<String, String> attributes;

	public PrincipalImpl(String name) {
		super();
		this.name = name;
	}

	public PrincipalImpl(String name, Map<String, String> attributes) {
		super();
		this.name = name;
		this.attributes = attributes;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		// 示例：username=test,password=123
		return this.attributes.toString();
	}

}
