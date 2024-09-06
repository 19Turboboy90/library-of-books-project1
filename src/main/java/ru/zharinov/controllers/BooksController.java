package ru.zharinov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zharinov.dao.BookDao;
import ru.zharinov.models.Book;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookDao bookDao;

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDao.getAllBooks());
        return "books/show-books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new-book";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new-book";
        }
        bookDao.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String infoBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getBookById(id));
        return "books/show-book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getBookById(id));
        return "books/edit-book";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDao.updateBookById(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.deleteBookById(id);
        return "redirect:/books";
    }
}
