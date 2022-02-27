package com.anupam.ProjectManagement.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anupam.ProjectManagement.demo.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long>{

	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
	
	ProjectTask findByProjectSequence(String sequence);
	
}
