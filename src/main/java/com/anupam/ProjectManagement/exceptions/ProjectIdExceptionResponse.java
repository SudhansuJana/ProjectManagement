package com.anupam.ProjectManagement.exceptions;

public class ProjectIdExceptionResponse {

	private String projectIdentifier;

	public ProjectIdExceptionResponse(String projectIdentifier) {
		super();
		this.projectIdentifier = projectIdentifier;
	}

	public String getprojectIdentifier() {
		return projectIdentifier;
	}

	public void setprojectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

}
