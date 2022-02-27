package com.anupam.ProjectManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anupam.ProjectManagement.demo.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long>{
	
	Backlog findByProjectIdentifier(String Identifier);
	
}
