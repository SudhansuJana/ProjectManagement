package com.anupam.ProjectManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException
{
	public ProjectIdException(String msg) {
		super(msg);
	}	
}