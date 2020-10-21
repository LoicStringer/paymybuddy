package com.paymybuddy.controller;

import java.time.LocalDateTime;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.paymybuddy.exception.BankProcessFailedException;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.exception.NegativeAmountException;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.exception.UniqueConstraintViolationException;
import com.paymybuddy.exception.WrongPasswordException;
import com.paymybuddy.responseentity.ExceptionResponse;

/**
 * <p>Reuniting exceptions treatments in a this single class.
 * Each method is associated to an exception and retrieve a detailed response message.</p>
 * @author newbie
 *@see ExceptionHandler
 */
@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<ExceptionResponse> handleWrongPasswordExceptionException(WrongPasswordException ex) {

		ExceptionResponse exceptionResponse = exceptionResponseBuild(ex);

		return new ResponseEntity<ExceptionResponse>(exceptionResponse, getHttpStatusFromException(ex));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

		ExceptionResponse exceptionResponse = exceptionResponseBuild(ex);

		return new ResponseEntity<ExceptionResponse>(exceptionResponse, getHttpStatusFromException(ex));
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ExceptionResponse> handleInsufficientBalanceException(InsufficientBalanceException ex) {

		ExceptionResponse exceptionResponse = exceptionResponseBuild(ex);

		return new ResponseEntity<ExceptionResponse>(exceptionResponse, getHttpStatusFromException(ex));
	}
	
	@ExceptionHandler(NegativeAmountException.class)
	public ResponseEntity<ExceptionResponse> handleNegativeAmountException(NegativeAmountException ex) {

		ExceptionResponse exceptionResponse = exceptionResponseBuild(ex);

		return new ResponseEntity<ExceptionResponse>(exceptionResponse, getHttpStatusFromException(ex));
	}
	
	@ExceptionHandler(BankProcessFailedException.class)
	public ResponseEntity<ExceptionResponse> handleBankProcessException(BankProcessFailedException ex) {

		ExceptionResponse exceptionResponse = exceptionResponseBuild(ex);

		return new ResponseEntity<ExceptionResponse>(exceptionResponse, getHttpStatusFromException(ex));
	}
	
	@ExceptionHandler(UniqueConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> handleUniqueConstraintException(UniqueConstraintViolationException ex) {

		ExceptionResponse exceptionResponse = exceptionResponseBuild(ex);

		return new ResponseEntity<ExceptionResponse>(exceptionResponse, getHttpStatusFromException(ex));
	}
	
	
	private ExceptionResponse exceptionResponseBuild(Exception ex) {

		String statusCode = getStatusCodeFromException(ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), statusCode,
				ex.getClass().getSimpleName(), ex.getMessage());

		return exceptionResponse;
	}

	private HttpStatus getHttpStatusFromException(Exception ex) {

		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		HttpStatus status = responseStatus.value();

		return status;
	}

	private String getStatusCodeFromException(Exception ex) {

		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		HttpStatus status = responseStatus.code();

		return status.toString();
	}

}
