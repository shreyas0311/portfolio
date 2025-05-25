package com.portfolio.my_portfolio.admin.messages;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

    private static final Logger logger = LoggerFactory.getLogger(MessageRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

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
        logger.info("Fetching all messages");
        try {
            List<GetMessages> messages = jdbcTemplate.query(sql, new DataClassRowMapper<>(GetMessages.class));
            logger.info("Successfully retrieved {} messages", messages.size());
            return messages;
        } catch (DataAccessException e) {
            logger.error("Error retrieving messages from database", e);
            return Collections.emptyList();
        }
    }

    public boolean deleteMessage(int id) {
        String sql = "DELETE FROM message WHERE id = :id";
        logger.info("Attempting to delete message with id [{}]", id);
        try {
            int rowsAffected = jdbcTemplate.update(sql, Map.of("id", id));
            logger.info("Deleted [{}] rows for message id [{}]", rowsAffected, id);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            logger.error("Error deleting message with id [{}]", id, e);
            return false;
        }
    }
}