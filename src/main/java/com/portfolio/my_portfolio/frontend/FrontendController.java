package com.portfolio.my_portfolio.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class FrontendController {

    static record SendMessage(
            String name,
            String email,
            String message) {
    }

    @Autowired
    private FrontendService frontendService;

    @GetMapping
    public String showFrontend() {
        return "frontend/index";
    }

    @PostMapping("/send-message")
    public RedirectView sendMessage(@ModelAttribute SendMessage sendMessage, RedirectAttributes redirectAttributes) {
        frontendService.sendMessage(sendMessage);
        redirectAttributes.addFlashAttribute("success", "Message sent successfully!");
        return new RedirectView("/");
    }
}
