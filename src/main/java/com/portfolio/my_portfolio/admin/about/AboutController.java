package com.portfolio.my_portfolio.admin.about;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/about")
public class AboutController {

    @GetMapping
    public String about(Model model) {
        String aboutContent = "hello!"; // Replace with actual content from your service

        model.addAttribute("aboutContent", aboutContent);
        return "admin/about";
    }
}