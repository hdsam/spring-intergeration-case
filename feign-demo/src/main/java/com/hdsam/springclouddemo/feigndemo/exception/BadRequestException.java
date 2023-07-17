package com.hdsam.springclouddemo.feigndemo.exception;

/**
 * BadRequestException
 *
 * @author Yeo
 * @date 2023/6/25
 */
public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
	}
}
