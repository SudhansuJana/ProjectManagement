package com.anupam.ProjectManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anupam.ProjectManagement.demo.Project;

@Repository

public interface ProjectRepository extends CrudRepository<Project, Long> {

	@Override
	Iterable<Project> findAllById(Iterable<Long> iterable);

	Iterable<Project> findAllByProjectLeader(String username);

	Project findByProjectIdentifier(String projectId);

	Iterable<Project> findAll();
}
