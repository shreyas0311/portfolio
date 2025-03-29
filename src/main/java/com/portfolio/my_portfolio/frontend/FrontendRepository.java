package com.portfolio.my_portfolio.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portfolio.my_portfolio.frontend.FrontendController.SendMessage;

@Repository
public class FrontendRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void sendMessage(SendMessage sendMessage) {
        String sql = "INSERT INTO message (name, email, message) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, sendMessage.name(), sendMessage.email(), sendMessage.message());
    }
    
}
