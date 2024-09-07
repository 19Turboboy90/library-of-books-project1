package ru.zharinov.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private int id;
    @NotEmpty(message = "The title should not be empty")
    private String title;
    @NotEmpty(message = "The author should not be empty")
    private String author;
    @Min(value = 0, message = "The year must be at least 0")
    @Max(value = 2100, message = "The year must be at least 0 and no more than 2100")
    private int year;
    private Person person;
}
