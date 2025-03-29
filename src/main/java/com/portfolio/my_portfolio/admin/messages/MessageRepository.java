package com.portfolio.my_portfolio.admin.messages;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public MessageRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static record GetMessages(
            int id,
            String name,
            String email,
            String message,
            LocalDateTime createdAt) {
    }

    public List<GetMessages> getMessages() {
        String sql = "SELECT id, name, email, message, created_at FROM message ORDER BY created_at ASC";
        return jdbcTemplate.query(sql, new DataClassRowMapper<>(GetMessages.class));
    }
}