package com.portfolio.my_portfolio.admin.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.my_portfolio.config.SessionManagement;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    private final LoginService loginService;
    private final SessionManagement sessionManagement;

    public LoginController(LoginService loginService, SessionManagement sessionManagement) {
        this.loginService = loginService;
        this.sessionManagement = sessionManagement;
    }

    public record LoginForm(String username, String password) {
    }

    @GetMapping
    public String showLoginForm(HttpSession session, Model model, HttpServletResponse response) {
        if (sessionManagement.isAuthenticated(session)) {
            addNoCacheHeaders(response);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("loginForm", true);
        addNoCacheHeaders(response);
        return "admin/login";
    }

    @PostMapping
    public String processLogin(@ModelAttribute LoginForm loginForm, HttpSession session, HttpServletResponse response,
            Model model) {
        if (loginService.validateCredentials(loginForm)) {
            sessionManagement.createSession(session);
            addNoCacheHeaders(response);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("loginForm", new LoginForm("", ""));
        return "redirect:/admin?error=true";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, HttpServletResponse response) {
        if (!sessionManagement.isAuthenticated(session)) {
            addNoCacheHeaders(response);
            return "redirect:/admin";
        }
        addNoCacheHeaders(response);
        return "admin/dashboard";
    }

    @PostMapping("/logout")
    public String handleLogout(HttpSession session) {
        sessionManagement.invalidateSession(session);
        return "admin/logout";
    }

    private void addNoCacheHeaders(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }
}
