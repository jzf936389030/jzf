package com.yimeng.seed.organ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *404  用户请求正确  但是资源不存在 
 * @author fly
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	public NotFoundException(String message)
	{
		super(message);
	}
}
