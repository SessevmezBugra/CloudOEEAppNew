package com.oee.advice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class IMExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleExceptions(Exception exception, WebRequest request){
		Logger logger = LoggerFactory.getLogger(IMExceptionHandler.class);
		logger.error("Controller Exception : " + exception.getMessage());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),exception.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}