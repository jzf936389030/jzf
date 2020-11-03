package com.yimeng.seed.organ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandle {
	class ExceptionResponse {
		// 响应业务状态
		private Integer status;
		// 响应消息
		private String msg;

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	@ExceptionHandler(InvalidArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse handleInvalidArgumentException(InvalidArgumentException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(400);
		response.setMsg(ex.getMessage());
		return response;
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ExceptionResponse handleNotFoundException(NotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(404);
		response.setMsg(ex.getMessage());
		return response;
	}

}
