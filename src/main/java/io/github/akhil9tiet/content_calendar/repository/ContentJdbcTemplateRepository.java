package io.github.akhil9tiet.content_calendar.repository;

import io.github.akhil9tiet.content_calendar.model.Content;
import io.github.akhil9tiet.content_calendar.model.Status;
import io.github.akhil9tiet.content_calendar.model.Type;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    * This private static method maps a ResultSet row to a Content object.
       It extracts the data from the result set using appropriate getter methods and constructs a Content object.
       SQL exceptions may be thrown if issues occur while accessing the database.
    *
    */
    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("desc"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getObject("date_created", LocalDateTime.class),
                rs.getObject("date_updated",LocalDateTime.class),
                rs.getString("url"));
    }

    public List<Content> findAll() {
        String sql = "SELECT * FROM Content";
        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }

    public List<Content> getAllContent() {
        return findAll();
    }

    public Optional<Content> findById(Integer id) {
        String sql = "SELECT * FROM Content WHERE id=?";
        try {
            Content content = jdbcTemplate.queryForObject(sql, ContentJdbcTemplateRepository::mapRow, id);
            return Optional.ofNullable(content);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean existsById(Integer id) {
        String sql = "SELECT COUNT(*) FROM Content WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public void save(Content content) {
        if (content.id() != null && existsById(content.id())) {
            // Update existing
            String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
            jdbcTemplate.update(sql, content.title(), content.desc(), content.status().name(),
                    content.contentType().name(), content.url(), content.id());
        } else {
            // Insert new
            String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, url) VALUES (?, ?, ?, ?, NOW(), ?)";
            jdbcTemplate.update(sql, content.title(), content.desc(), content.status().name(),
                    content.contentType().name(), content.url());
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /*
     * Executes a SQL query to retrieve all content records from the Content table.
     Uses jdbcTemplate.query with the mapRow method to convert each row in the
     result set to a Content object.
     */
    public void createContent(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";
        jdbcTemplate.update(sql, title, desc, status.name(), contentType.name(), URL);
    }

    public void updateContent(int id, String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status.name(), contentType.name(), URL, id);
    }

    public void deleteContent(int id) {
        delete(id);
    }

    public Content getContent(int id) {
        return findById(id).orElse(null);
    }
}
