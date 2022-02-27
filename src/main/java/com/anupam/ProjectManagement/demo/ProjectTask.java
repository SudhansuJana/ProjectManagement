package com.anupam.ProjectManagement.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "project_task")
public class ProjectTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "project_sequence",updatable = false, unique = true)
	private String projectSequence;
	
	@NotBlank(message = "Please include a Project Summary")
	private String summary;
	private String acceptanceCriteria;
	private String status;
	private Integer priority;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date dueDate;
	//ManyToOne with Backlog
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "backlog_id",updatable = false,nullable = false)
	@JsonIgnore
	private Backlog backlog;
	

	@Column(updatable = false)
	private String projectIdentifier;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date created_At;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date updated_At;
	
	
	@PrePersist
	protected void onCreate() {
		this.created_At=new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At=new Date();
	}
	
	
	public ProjectTask() {
		
	}
	
	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectSequence() {
		return projectSequence;
	}

	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}

	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	@Override
	public String toString() {
		return "ProjectTask [id=" + id + ", projectSequence=" + projectSequence + ", summary=" + summary
				+ ", acceptanceCriteria=" + acceptanceCriteria + ", status=" + status + ", priority=" + priority
				+ ", dueDate=" + dueDate + ", projectIdentifier=" + projectIdentifier + ", created_At=" + created_At
				+ ", updated_At=" + updated_At + "]";
	}
	
	
}
