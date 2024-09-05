package ru.zharinov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zharinov.dao.BookDAO;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookDAO bookDAO;

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "book/show-books";
    }
}
