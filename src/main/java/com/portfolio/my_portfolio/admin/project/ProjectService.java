package com.portfolio.my_portfolio.admin.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.my_portfolio.admin.project.ProjectRepository.ProjectDetails;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDetails> getProjects() {
        return projectRepository.getProjects();
    }

    public ProjectDetails getProjectById(long id) {
        return projectRepository.findById(id);
    }
}
