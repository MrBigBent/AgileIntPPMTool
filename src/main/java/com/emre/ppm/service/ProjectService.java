package com.emre.ppm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emre.ppm.domain.Project;
import com.emre.ppm.exceptions.ProjectIdException;
import com.emre.ppm.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdate(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			System.out.println("Project Identifier var");
			throw new ProjectIdException("Project Id : " + project.getProjectIdentifier().toUpperCase() + " already exists");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project Id : " +projectId+ " doesnt exists");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Cannot Projecct with Id "+projectId+" This project doesnt exists");
		}
		
		projectRepository.delete(project);
	}
}
