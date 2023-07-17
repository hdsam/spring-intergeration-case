package com.hdsam.springclouddemo.springsecuritydemo.security;

/**
 * ApplicationUserPermission
 *
 * @author Yeo
 * @date 2023/7/13
 */
public enum ApplicationUserPermission {

	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");

	private final String permission;


	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
