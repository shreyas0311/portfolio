package com.portfolio.my_portfolio.config;

import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionManagement {
    private static final String AUTH_KEY = "ADMIN_AUTHENTICATED";

    public void createSession(HttpSession session) {
        session.setAttribute(AUTH_KEY, true);
    }

    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute(AUTH_KEY) != null;
    }

    public void invalidateSession(HttpSession session) {
        session.invalidate();
    }
}
