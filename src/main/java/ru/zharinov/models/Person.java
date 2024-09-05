package ru.zharinov.models;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private int id;
    private String fullName;
    private int yearOfBirth;
    private List<Book> bookList;
}
