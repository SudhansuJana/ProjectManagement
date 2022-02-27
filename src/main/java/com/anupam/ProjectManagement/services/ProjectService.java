package com.anupam.ProjectManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anupam.ProjectManagement.demo.Backlog;
import com.anupam.ProjectManagement.demo.Project;
import com.anupam.ProjectManagement.demo.User;
import com.anupam.ProjectManagement.exceptions.ProjectIdException;
import com.anupam.ProjectManagement.exceptions.ProjectNotFoundException;
import com.anupam.ProjectManagement.repositories.BacklogRepository;
import com.anupam.ProjectManagement.repositories.ProjectRepository;
import com.anupam.ProjectManagement.repositories.UserRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private UserRepository userRepository;

	public Project saveOrUpdateProject(Project project, String username) {

		if (project.getId() != null) {

			Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());

			if (existingProject != null && (!existingProject.getProjectLeader().equals(username))) {
				throw new ProjectNotFoundException("Project Not Found in your account");
			} else if (existingProject == null) {
				throw new ProjectNotFoundException("Project with ID: '" + project.getProjectIdentifier()
						+ "' can't be updated because it doesn't exists");
			}
		}

		try {
			User user = userRepository.findByUsername(username);
			project.setUser(user);
			project.setProjectLeader(user.getUsername());

			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			if (project.getId() == null) {

				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			if (project.getId() != null) {
				project.setBacklog(
						backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			return projectRepository.save(project);

		} catch (Exception e) {
			throw new ProjectIdException(
					"Project '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}
	}

	public Project findByProjectIdentifier(String projectId, String username) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if (project == null)
			throw new ProjectIdException("Project Id: " + projectId + " does not exists");

		if (!project.getProjectLeader().equals(username))
			throw new ProjectNotFoundException("Project not Found in Your Account");

		return project;
	}

	public Iterable<Project> findAllProjects(String username) {
		return projectRepository.findAllByProjectLeader(username);
	}

	public void deleteProjectByIdentifier(String projectId, String username) {
//		Project project = projectRepository.findByProjectIdentifier(projectId);
//		if(project == null)
//			throw new ProjectIdException("Cannot Project with Id '"+projectId+"' .This Project does not exist");

//		projectRepository.delete(project);

		projectRepository.delete(findByProjectIdentifier(projectId,username));
	}
}
