package ru.zharinov.models;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private int id;

    @NotEmpty(message = "The full name should not be empty")
    @Size(min = 2, max = 100, message = "The full name should be between 2 and 100 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+",
            message = "The input format should be as follows - 'Ivanov Ivan Ivanovich'")
    private String fullName;

    @Min(value = 1900, message = "The year must be at least 1900")
    @Max(value = 2100, message = "The year should not be more than 2100")
    private int yearOfBirth;
    private List<Book> bookList;
}
