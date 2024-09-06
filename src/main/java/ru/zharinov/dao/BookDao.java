package ru.zharinov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zharinov.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate template;

    @Autowired
    public BookDao(JdbcTemplate template) {
        this.template = template;
    }

    private static final String GET_ALL_BOOKS = """
            SELECT id, title, author, year
            FROM book;
            """;

    private static final String GET_BOOK_BY_ID = """
            SELECT id, title, author, year
            FROM book
            WHERE id=?;
            """;

    private static final String SAVE_BOOK = """
            INSERT INTO book (title, author, year) VALUES (?,?,?)
            """;

    private static final String UPDATE_BOOK_BY_ID = """
            UPDATE book\s
            SET title = ?, author = ?, year=?
            WHERE id=?
            """;

    private static final String DELETE_BOOK_BY_ID = """
            DELETE FROM book
            WHERE id = ?;
            """;

    public List<Book> getAllBooks() {
        return template.query(GET_ALL_BOOKS, new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> getBookById(int id) {
        return template.query(GET_BOOK_BY_ID, new BeanPropertyRowMapper<>(Book.class), id)
                .stream().filter(book -> book.getId() == id).findFirst();
    }

    public void saveBook(Book book) {
        template.update(SAVE_BOOK, book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void updateBookById(int id, Book book) {
        template.update(UPDATE_BOOK_BY_ID, book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBookById(int id) {
        template.update(DELETE_BOOK_BY_ID, id);
    }
}
