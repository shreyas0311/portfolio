package com.portfolio.my_portfolio.admin.login;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<AdminUser> validateCredentials(String username, String password) {
        String sql = "SELECT id, username, password FROM admin WHERE username = ? AND password = ?";
        return jdbcTemplate.query(sql,
                (rs, _) -> new AdminUser(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password")),
                username, password).stream().findFirst();
    }

    public record AdminUser(long id, String username, String password) {
    }
}
