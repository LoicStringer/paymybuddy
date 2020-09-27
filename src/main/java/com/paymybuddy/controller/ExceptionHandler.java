package com.paymybuddy.controller;

import java.time.LocalDateTime;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.paymybuddy.responseentity.ExceptionResponse;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

private ExceptionResponse exceptionResponseBuild(Exception ex) {
		
		String statusCode = getStatusCodeFromException(ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),statusCode, ex.getClass().getSimpleName(), ex.getMessage());
		
		return exceptionResponse;
	}
	
	private HttpStatus getHttpStatusFromException(Exception ex) {

		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		HttpStatus status = responseStatus.value();
		
		return status ;
	}
	
	private String getStatusCodeFromException(Exception ex) {
		
		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		HttpStatus status = responseStatus.code();
		
		return status.toString();
	}
	
}
