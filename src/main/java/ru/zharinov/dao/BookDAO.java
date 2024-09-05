package ru.zharinov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zharinov.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ALL_BOOKS = """
            SELECT id, title, author, year
            FROM book;
            """;

    public List<Book> getAllBooks() {
        return jdbcTemplate.query(GET_ALL_BOOKS, new BeanPropertyRowMapper<>(Book.class));
    }
}
