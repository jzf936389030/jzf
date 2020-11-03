package com.yimeng.seed.organ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 403  无权限  登录的限制
 * @author fly
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN)
public class ForbiddenException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ForbiddenException(String message)
	{
		super(message);
	}
}
