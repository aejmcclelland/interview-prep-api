package uk.co.amcclelland.interview_prep_api.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbPingController {

    private final JdbcTemplate jdbcTemplate;

    public DbPingController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/db-ping")
    public ResponseEntity<String> ping() {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            if (result != null && result == 1) {
                return ResponseEntity.ok("Database connection successful!");
            }
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Database connection failed.");
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Database connection failed.");
        }
    }
}
