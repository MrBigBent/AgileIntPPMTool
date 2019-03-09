package com.emre.ppm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emre.ppm.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

}