package com.anupam.ProjectManagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anupam.ProjectManagement.demo.Backlog;
import com.anupam.ProjectManagement.demo.ProjectTask;
import com.anupam.ProjectManagement.exceptions.ProjectNotFoundException;
import com.anupam.ProjectManagement.repositories.BacklogRepository;
import com.anupam.ProjectManagement.repositories.ProjectRepository;
import com.anupam.ProjectManagement.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	BacklogRepository backlogRepository;

	@Autowired
	ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectService projectService;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask,String username) {

		
// ProjectServiceTask to be added to a specific project ,project !=null, Backlog exists
		//Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		Backlog backlog = projectService.findByProjectIdentifier(projectIdentifier,username).getBacklog();
// set the Backlog to ProjectTask
		projectTask.setBacklog(backlog);
// We want our project sequence be like this: IDPRO-1 , IDPRO-2 ...100,101
		Integer BacklogSequence = backlog.getPTSequence();

// update the Backlog sequence
		BacklogSequence++;
		backlog.setPTSequence(BacklogSequence);
// Add sequence to Project Task
		projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
// Initial Priority when Priority null
		if (projectTask.getPriority() == null || projectTask.getPriority()==0) { // In future we need projectTask.getPriority() == 0 to handle the form
			projectTask.setPriority(3);
		}
// Initial status when status is null
		if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO_DO");
		}

		return projectTaskRepository.save(projectTask);
	 
	}

	
	public Iterable<ProjectTask> findBacklogById(String backlog_id,String username) {
		
//		Project project=projectRepository.findByProjectIdentifier(backlog_id,username);
//		if(project == null)
//		{
//			throw new ProjectNotFoundException("Project with Id: "+backlog_id+" does not exists");
//		}
        projectService.findByProjectIdentifier(backlog_id, username);
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	
	public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id,String username) {
		// make sure that we are searching an existing backlog
		
//		Backlog backlog=backlogRepository.findByProjectIdentifier(backlog_id);
//		if(backlog == null)
//			throw new ProjectNotFoundException("Project with Id: "+backlog_id+" does not exists");
		
		projectService.findByProjectIdentifier(backlog_id, username);
		
		// make sure that our task exists
		ProjectTask projectTask= projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask == null)
			throw new ProjectNotFoundException("Project task not Found");
		
		// make sure that the back_log/project id in the path corresponds to the right project
		if(!projectTask.getProjectIdentifier().equals(backlog_id))
			throw new ProjectNotFoundException("Project task '"+pt_id+"' does not exists in Project '"+backlog_id+"' ");
		
		return projectTaskRepository.findByProjectSequence(pt_id);
	}

	public ProjectTask updateByProjectSequence(ProjectTask updateTask,String backlog_id,String pt_id,String username) {
		
		ProjectTask projectTask =findPTByProjectSequence(backlog_id,pt_id,username);
		
		projectTask = updateTask;
		
		return projectTaskRepository.save(projectTask);
	}
	
	public void deletePTByProjectSequence(String backlog_id,String pt_id,String username) {
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id,username);
		
//		Backlog backlog = projectTask.getBacklog();
//		List<ProjectTask> pts = backlog.getProjectTasks();
//		pts.remove(projectTask);
//		backlogRepository.save(backlog);
		
		projectTaskRepository.delete(projectTask);
	}
}
