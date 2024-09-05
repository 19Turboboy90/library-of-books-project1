package ru.zharinov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zharinov.dao.BookDAO;
import ru.zharinov.models.Book;

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

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new-book";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/new-book";
        }
        bookDAO.saveBook(book);
        return "redirect:/books";
    }
}
