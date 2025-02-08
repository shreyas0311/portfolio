package com.portfolio.my_portfolio.admin.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public record ProjectDetails(
            long id,
            String projectName,
            String description,
            String projectLink,
            LocalDate projectStartDate,
            LocalDate projectEndDate,
            String duration,
            String skills,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
    }

    public List<ProjectDetails> getProjects() {
        String sql = "SELECT * FROM projects";
        return jdbcTemplate.query(sql, (rs, _) -> new ProjectDetails(
                rs.getLong("id"),
                rs.getString("project_name"),
                rs.getString("description"),
                rs.getString("project_link"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date").toLocalDate(),
                rs.getString("duration"),
                rs.getString("skills"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()));
    }

    public ProjectDetails findById(long id) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ProjectRowMapper(), id);
    }

    private class ProjectRowMapper implements RowMapper<ProjectDetails> {
        @Override
        public ProjectDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ProjectDetails(
                    rs.getLong("id"),
                    rs.getString("project_name"),
                    rs.getString("description"),
                    rs.getString("project_link"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getString("duration"),
                    rs.getString("skills"), null, LocalDateTime.now());
        }
    }
}