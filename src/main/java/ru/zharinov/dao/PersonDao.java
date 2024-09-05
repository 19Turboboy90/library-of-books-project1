package ru.zharinov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zharinov.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate template;

    @Autowired
    public PersonDao(JdbcTemplate template) {
        this.template = template;
    }

    private static final String GET_ALL_PEOPLE = """
            SELECT id, full_name, year_of_birth
            FROM person;
            """;

    private static final String GET_PERSON_BT_ID = """
            SELECT id, full_name, year_of_birth
            FROM person
            WHERE id = ?;
            """;

    private static final String SAVE_PERSON = """
            INSERT INTO person VALUES (?, ?);
            """;

    private static final String UPDATE_PERSON_BY_ID = """
            UPDATE person p
            SET full_name = ?, year_of_birth =?
            WHERE id = ?;
            """;

    private static final String DELETE_PERSON = """
            DELETE FROM person
            WHERE id = ?;
            """;

    public List<Person> getAllPeople() {
        return template.query(GET_ALL_PEOPLE, new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> getPersonById(int id) {
        return template.query(GET_PERSON_BT_ID, new BeanPropertyRowMapper<>(Person.class), id)
                .stream().filter(person -> person.getId() == id).findFirst();
    }

    public void savePerson(Person person) {
        template.update(SAVE_PERSON, person.getFullName(), person.getYearOfBirth());
    }

    public void updatePersonById(int id, Person person) {
        template.update(UPDATE_PERSON_BY_ID, person.getFullName(), person.getYearOfBirth(), id);
    }

    public void deletePersonById(int id) {
        template.update(DELETE_PERSON, id);
    }
}
