package com.portfolio.my_portfolio.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.portfolio.my_portfolio.admin.project.ProjectService;

@Controller
@RequestMapping("/")
public class FrontendController {

    static record SendMessage(
            String name,
            String email,
            String message) {
    }

    private final FrontendService frontendService;
    private final ProjectService projectService;
    private static final Logger logger = LoggerFactory.getLogger(FrontendController.class);

    public FrontendController(FrontendService frontendService, ProjectService projectService) {
        this.projectService = projectService;
        this.frontendService = frontendService;
    }

    @GetMapping
    public String showFrontend(Model model) {
        var projects = projectService.getProjects();
        model.addAttribute("projects", projects);
        return "frontend/index";
    }

    @PostMapping("/send-message")
    public RedirectView sendMessage(@ModelAttribute SendMessage sendMessage, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Sending message from: {}", sendMessage.email());
            frontendService.sendMessage(sendMessage);
            redirectAttributes.addFlashAttribute("success", "Message sent successfully!");
        } catch (Exception e) {
            logger.error("Error sending message", e);
            redirectAttributes.addFlashAttribute("error", "Failed to send message. Please try again.");
        }
        return new RedirectView("/");
    }
}