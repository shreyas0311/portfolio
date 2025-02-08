package com.portfolio.my_portfolio.admin.project;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.my_portfolio.admin.project.ProjectRepository.ProjectDetails;
import com.portfolio.my_portfolio.config.SessionManagement;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SessionManagement sessionManagement;

    private List<String> technicalSkills = Arrays.asList(
            "Java", "Spring Boot", "Spring MVC", "Spring Security",
            "HTML", "CSS", "JavaScript", "TypeScript",
            "React", "Angular", "Vue.js",
            "Python", "Django", "Flask",
            "MySQL", "PostgreSQL", "MongoDB",
            "Docker", "Kubernetes", "AWS",
            "Git", "Jenkins", "Maven", "Gradle",
            "JUnit", "Mockito", "REST API",
            "Microservices", "Node.js", "Express.js");

    @GetMapping
    public String showProjects(Model model, HttpSession session, HttpServletResponse response) {
        if (!sessionManagement.isAuthenticated(session)) {
            addNoCacheHeaders(response);
            return "redirect:/admin";
        }

        List<ProjectDetails> projects = projectService.getProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("technicalSkills", technicalSkills);
        model.addAttribute("currentPage", "projects");
        addNoCacheHeaders(response);
        return "admin/project";
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public ProjectDetails getProjectDetails(@PathVariable long id) {
        return projectService.getProjectById(id);
    }

    private void addNoCacheHeaders(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }

}
